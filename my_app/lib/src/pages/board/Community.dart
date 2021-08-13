import 'package:carousel_slider/carousel_slider.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:my_app/controller/home_controller.dart';
import 'package:my_app/data/network.dart';
import 'package:my_app/model/BoardVO.dart';
import 'package:my_app/src/pages/board/BoardInfo.dart';

class Community extends StatefulWidget {
  final User user;
  const Community({Key? key, required this.user}) : super(key: key);

  @override
  _CommunityState createState() => _CommunityState();
}

final List<String> imgList = [];

class _CommunityState extends State<Community> {
  // 게시글
  late Future<dynamic> _boards;
  // 베스트 게시글
  late Future<dynamic> _bestBoards;

  final HomeController controller = Get.put(HomeController());

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _boards = boardOrder('/board/getList');
    _bestBoards = boardOrder('/board/bestList');

    print(_bestBoards.toString());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // backgroundColor: Colors.brown[100],
      appBar: AppBar(
        title: Text('커뮤니티 '),
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          controller: controller.scrollController,
          child: Column(
            children: <Widget>[
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      '베스트',
                      style:
                          TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                    ),
                    SizedBox(
                      height: 20.0,
                    ),
                    getRankComponent(_bestBoards),
                    SizedBox(
                      height: 30.0,
                    ),
                    Text(
                      '커뮤니티',
                      style:
                          TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                    ),
                    Row(
                      children: [
                        ElevatedButton(onPressed: () {}, child: Text('최신순')),
                        SizedBox(
                          width: 5,
                        ),
                        ElevatedButton(
                            onPressed: () {}, child: Text('댓글 많은 순')),
                        SizedBox(
                          width: 5,
                        ),
                        ElevatedButton(onPressed: () {}, child: Text('좋아요 수'))
                      ],
                    ),
                  ],
                ),
              ),
              getDataList(_boards)
            ],
          ),
        ),
      ),
    );
  }

  /************************************************************************* start 베스트 컴포넌트********************************************************************************** */

// 게시글 뿌려주는곳
  FutureBuilder getDataList(Future<dynamic> _boards) {
    return FutureBuilder(
      future: _boards,
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        print(snapshot.data.toString());
        if (!snapshot.hasData)
          return Center(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            crossAxisAlignment: CrossAxisAlignment.center,
            children: [
              CircularProgressIndicator(),
            ],
          ));
        return NotificationListener<ScrollNotification>(
          onNotification: (ScrollNotification scrollInfo) {
            print(scrollInfo.metrics.pixels);
            if (scrollInfo.metrics.pixels ==
                scrollInfo.metrics.maxScrollExtent) {
              print('reload');
            }
            return false;
          },
          child: ListView.builder(
              primary: false,
              shrinkWrap: true,
              padding: const EdgeInsets.all(8),
              itemCount: snapshot.data.length,
              itemBuilder: (context, index) {
                // num number = index + 1;
                dynamic root = snapshot.data[index];
                // 타이틀
                String title = root['BTITLE'].toString();
                // 컨텐츠
                String content = root['BCONT'].toString();
                // 이미지 (파일 이미지 나중에 추가)
                CircleAvatar image = CircleAvatar(
                    backgroundImage: AssetImage('assets/fire.png'));
                // 이름
                String name = root['MID'].toString();
                // 글 올린 날짜
                String date = root['BDATE'].toString();
                // 피드 시간
                String rec = root['MT'].toString();

                return Card(
                  child: ListTile(
                    leading: image,
                    onTap: () {
                      Get.to(BoardInfo(param: root, user: widget.user),
                          transition: Transition.zoom);
                    },
                    title: Column(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          title,
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
                        ),
                        SizedBox(
                          height: 7,
                        ),
                        RichText(
                          overflow: TextOverflow.ellipsis,
                          strutStyle: StrutStyle(fontSize: 12.0),
                          text: TextSpan(
                              style: TextStyle(color: Colors.black),
                              text: content),
                        ),
                        SizedBox(
                          height: 18,
                        ),
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            // Text('Name | Time '),
                            Text(
                              name + ' | $rec',
                              style: TextStyle(fontSize: 12.0),
                            ),
                            _getIcon(root)
                          ],
                        )
                      ],
                    ),
                    // leading: ,
                  ),
                );
              }),
        );
      },
    );
  }

/************************************************************************* end 게시글 컴포넌트********************************************************************************** */

/************************************************************************* start 베스트 컴포넌트********************************************************************************** */

// 랭크 보더스
  FutureBuilder getRankComponent(Future<dynamic> _bestBoards) {
    late Color _selectHoverColor;
    // late List _item = ;

    return FutureBuilder(
      future: _bestBoards,
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        print(snapshot.data.toString());
        // return Text(snapshot.data.toString());
        if (!snapshot.hasData)
          return Center(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircularProgressIndicator(),
            ],
          ));

        return Column(
          children: [
            CarouselSlider.builder(
                itemCount: snapshot.data.length,
                itemBuilder: (context, int itemIndex, int pageViewIndex) =>
                    _getBestCmponent(snapshot.data[itemIndex]),
                options: CarouselOptions(
                  autoPlay: true,
                  height: 120,
                  disableCenter: false,
                  viewportFraction: 0.6,
                  autoPlayInterval: const Duration(seconds: 4),
                  // aspectRatio: 16 / 2,
                )),
          ],
        );
      },
    );
  }

// 베스트 글 컴포넌트
  Widget _getBestCmponent(dynamic _bestBoards) {
    // 타이틀
    String title = _bestBoards['BTITLE'].toString();
    // 컨텐츠
    String content = _bestBoards['BCONT'].toString();
    // 이미지 (파일 이미지 나중에 추가)
    CircleAvatar image =
        CircleAvatar(backgroundImage: AssetImage('assets/fire.png'));
    // 이름
    String name = _bestBoards['MID'].toString();
    // 글 올린 날짜
    String date = _bestBoards['BDATE'].toString();

    return InkWell(
      // splashColor: Colors.lightGreenAccent,
      onTap: () {
        print('hoho');
      },
      child: SafeArea(
        child: Container(
          margin: EdgeInsets.all(2.0),
          padding: EdgeInsets.all(10.0),
          width: 220,
          child: Column(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: <Widget>[
                  Text(title,
                      style:
                          TextStyle(fontWeight: FontWeight.w700, fontSize: 14)),
                  _getIcon(_bestBoards)
                ],
              ),
              SizedBox(
                height: 7.0,
              ),
              RichText(
                overflow: TextOverflow.ellipsis,
                strutStyle: StrutStyle(fontSize: 12.0),
                text: TextSpan(
                    style: TextStyle(color: Colors.black), text: content),
              ),
              SizedBox(
                height: 7.0,
              ),
              Container(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.start,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    image,
                    SizedBox(
                      width: 10.0,
                    ),
                    Container(
                        child: Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                          Text(
                            '$name 님',
                            style: TextStyle(
                                fontSize: 12.0, fontWeight: FontWeight.w700),
                          ),
                          SizedBox(
                            height: 5.0,
                          ),
                          Text(date)
                        ]))
                  ],
                ),
              )
            ],
          ),
          decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(10)),
              boxShadow: [
                BoxShadow(
                  color: Colors.grey.withOpacity(0.5),
                  spreadRadius: 3,
                  blurRadius: 4,
                  offset: Offset(0, 3),
                )
              ]),
        ),
      ),
    );
  }

// 아이콘들 컨테이터
  Widget _getIcon(dynamic data) {
    // 방문자 수
    String visite = data['BVISIT'].toString();
    // 좋아요 수
    String blike = data['BLIKE'].toString();
    // 댓글 수
    String comments = data['REC'].toString();

    return Container(
      child: Row(
        children: [
          Icon(
            Icons.remove_red_eye_outlined,
            size: 12.0,
          ),
          SizedBox(
            width: 5,
          ),
          Text(visite),
          SizedBox(
            width: 5,
          ),
          Icon(
            Icons.favorite,
            color: Colors.pink,
            size: 12.0,
          ),
          SizedBox(
            width: 5,
          ),
          Text(blike),
          SizedBox(
            width: 5,
          ),
          Icon(
            Icons.message,
            size: 12.0,
          ),
          SizedBox(
            width: 5,
          ),
          Text(comments)
        ],
      ),
    );
  }

/************************************************************************* end 베스트 컴포넌트********************************************************************************** */

// 오더
  Future<dynamic> boardOrder(String order) async {
    // String url = "http://localhost:3000/login";
    // String url = "http://192.168.15.4:3000/login";
    String url = "http://192.168.15.4:8181" + order;
    Network network = Network(url);
    var data = await network.getJsonData();
    // Iterable l =data;
    // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
    // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
    return data;
  }
}
