import 'package:carousel_slider/carousel_slider.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:intl/intl.dart';
import 'package:my_app/data/network.dart';
import 'package:my_app/model/BoardVO.dart';

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

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _boards = boardOrder('/board/getList');
    _bestBoards = boardOrder('/board/bestList');

    print(_bestBoards.toString());
  }

  // Future<ProcessedData> createData() a

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 10.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    '베스트',
                    style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                  ),
                  SizedBox(
                    height: 20.0,
                  ),
                  // Expanded(child : getRankComponent(_bestBoards)),
                  getRankComponent(_bestBoards),
                  SizedBox(
                    height: 30.0,
                  ),
                  Text(
                    '커뮤니티',
                    style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                  ),
                  Row(
                    children: [
                      ElevatedButton(onPressed: () {}, child: Text('최신순')),
                      SizedBox(
                        width: 5,
                      ),
                      ElevatedButton(onPressed: () {}, child: Text('댓글 많은 순')),
                      SizedBox(
                        width: 5,
                      ),
                      ElevatedButton(onPressed: () {}, child: Text('좋아요 수'))
                    ],
                  ),
                ],
              ),
            ),
            // Expanded(child : getRankComponent(_bestBoards)),
            Expanded(child: getDataList(_boards))
          ],
        ),
      ),
    );
  }
}

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
          children: [
            CircularProgressIndicator(),
          ],
        ));
      return ListView.builder(
          padding: const EdgeInsets.all(8),
          itemCount: snapshot.data.length,
          itemBuilder: (context, index) {
            // num number = index + 1;
            dynamic root = snapshot.data[index];
            // 타이틀
            String title = root['btitle'].toString();
            // 컨텐츠
            String content = root['bcont'].toString();
            // 이미지 (파일 이미지 나중에 추가)
            CircleAvatar image =
                CircleAvatar(backgroundImage: AssetImage('assets/fire.png'));
            // 이름
            String name = root['mid'].toString();
            // 글 올린 날짜
            String date = root['bdate'].toString();

            return Card(
              child: ListTile(
                leading: image,
                onTap: () {},
                title: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      root['btitle'].toString(),
                      style:
                          TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    RichText(
                      overflow: TextOverflow.ellipsis,
                      strutStyle: StrutStyle(fontSize: 12.0),
                      text: TextSpan(
                          style: TextStyle(color: Colors.black),
                          text: root['bcont']),
                    ),
                    SizedBox(
                      height: 5,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        // Text('Name | Time '),
                        Text(name + ' | $date'),
                        Container(
                          child: Row(
                            children: [
                              Icon(Icons.remove_red_eye_outlined),
                              SizedBox(
                                width: 5,
                              ),
                              Text('2'),
                              SizedBox(
                                width: 5,
                              ),
                              Icon(
                                Icons.favorite,
                                color: Colors.pink,
                              ),
                              SizedBox(
                                width: 5,
                              ),
                              Text('32'),
                            ],
                          ),
                        ),
                      ],
                    )
                  ],
                ),
                // leading: ,
              ),
            );
          });
    },
  );
}

// 랭크 보더스
FutureBuilder getRankComponent(Future<dynamic> _bestBoards) {
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
          CarouselSlider(
              items: [
                Container(
                  width: 220,
                  child: Text('bannerCard 1'),
                  decoration: BoxDecoration(
                    color: Colors.teal[100],
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                  ),
                ),
                Container(
                  width: 220,
                  child: Text('bannerCard 2'),
                  decoration: BoxDecoration(
                    color: Colors.teal[100],
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                    // border: Border.all(color: Colors.black, width: 3),
                  ),
                ),
              ],
              options: CarouselOptions(
                autoPlay: true,
                height: 120,
                disableCenter: false,
                viewportFraction: 0.6,
                autoPlayInterval: const Duration(seconds: 4),
                // aspectRatio: 16 / 2,
              ))
        ],
      );
    },
  );
}

Future<dynamic> boardOrder(String order) async {
  // String url = "http://localhost:3000/login";
  // String url = "http://192.168.15.4:3000/login";
  String url = "http://192.168.15.4:8181" + order;
  Network network = await Network(url);
  var data = await network.getJsonData();
  // Iterable l =data;
  // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
  // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
  return data;
}
