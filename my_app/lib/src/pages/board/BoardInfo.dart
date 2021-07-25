import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/common/common_widget.dart';
import 'package:my_app/data/network.dart';

class BoardInfo extends StatefulWidget {
  final dynamic param;
  // final dynamic param;
  const BoardInfo({Key? key, this.param}) : super(key: key);

  @override
  _BoardInfoState createState() => _BoardInfoState();
}

class _BoardInfoState extends State<BoardInfo> {
  late Future<dynamic> _boarderInfo;
  late Future<dynamic> _repleInfo;

  CommonWidget _common = CommonWidget();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _boarderInfo = boardInfoOrder('/board/boardInfo', widget.param);
    _repleInfo = boardInfoOrder('/board/repleInfo', widget.param);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('글 작성 페이지'),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.all(5.0),
          child: Column(
            children: <Widget>[
              _getInfoComp(_boarderInfo),
            ],
          ),
        ),
      ),
    );
  }
}


/* *************************************** start 글 상세 페이지 ***************************************** */
// 글 상세 페이지 컴포넌트 (단건);
FutureBuilder _getInfoComp(Future<dynamic> _boarderInfo) {
  return FutureBuilder(
      future: _boarderInfo,
      builder: (BuildContext context, AsyncSnapshot snapshot) {
        final BorderRadius _baseBorderRadius = BorderRadius.circular(8);

        CircleAvatar image =
            CircleAvatar(backgroundImage: AssetImage('assets/fire.png'));

        const _padding = EdgeInsets.fromLTRB(15.0, 2.0, 10.0, 0.0);

        print(snapshot.data.toString());
        // var root = snapshot.data[0];
        if (!snapshot.hasData)
          return Center(
              child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircularProgressIndicator(),
            ],
          ));
        return Container(
          margin: EdgeInsets.only(top: 7.0),
          padding: EdgeInsets.all(5.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.start,
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                width: 15,
                height: 25,
                color: Colors.black,
              ),
              Container(
                padding: _padding,
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text('타이틀',
                        style: TextStyle(
                            fontWeight: FontWeight.w700, fontSize: 24)),
                    InkWell(
                      child: Container(
                        child: Icon(
                          Icons.share,
                          size: 24,
                        ),
                      ),
                      onTap: () {
                        print('공유');
                      },
                    )
                  ],
                ),
              ),
              SizedBox(
                height: 10.0,
              ),
              Container(
                padding: _padding,
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
                            'gogogo 님',
                            style: TextStyle(
                                fontSize: 12.0, fontWeight: FontWeight.w700),
                          ),
                          SizedBox(
                            height: 5.0,
                          ),
                          Text('modle')
                        ]))
                  ],
                ),
              ),
              SizedBox(
                height: 15.0,
              ),
              Container(
                padding: _padding,
                child: RichText(
                  // overflow: TextOverflow.ellipsis,
                  strutStyle: StrutStyle(fontSize: 12.0),
                  text: TextSpan(
                      style: TextStyle(color: Colors.black),
                      text:
                          '이헤인 바보 이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보이헤인 바보'),
                ),
              ),
              SizedBox(
                height: 30.0,
              ),
              _getIcon(snapshot.data),
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
        );
      });
}

/* *************************************** end 글 상세 페이지 ***************************************** */


// 아이콘들 컨테이터
Widget _getIcon(dynamic data) {
  // // 방문자 수
  // String visite = data['bvisit'].toString();
  // // 좋아요 수
  // String blike = data['blike'].toString();
  const _padding = EdgeInsets.fromLTRB(15.0, 2.0, 10.0, 0.0);

  return Container(
    padding: _padding,
    child: Row(
      children: [
        Icon(
          Icons.remove_red_eye_outlined,
          size: 12.0,
        ),
        SizedBox(
          width: 5,
        ),
        Text('test'),
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
        Text('test'),
      ],
    ),
  );
}

/* *********************************************** 통신부분 *********************************************************************** */
// 오더
Future<dynamic> boardInfoOrder(String order, dynamic param) async {
  // String url = "http://localhost:3000/login";
  // String url = "http://192.168.15.4:3000/login";
  String url = "http://192.168.15.4:8181" + order;
  Network network = Network(url);
  var data = await network.executePost(param);
  // Iterable l =data;
  // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
  // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
  return data;
}
