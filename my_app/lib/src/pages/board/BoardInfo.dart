import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
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

  @override
  void initState() {
    // TODO: implement initState
    _boarderInfo = boardInfoOrder('/board/boardInfo',widget.param);
    _repleInfo = boardInfoOrder('/board/repleInfo',widget.param);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('글 작성 페이지'),
        centerTitle: true,
      ),
      body: SafeArea(
        child: Container(
          child: Column(
            children: <Widget>[
              Text(widget.param.toString()),
              ButtonTheme(
                child: Center(
                  child: ElevatedButton(
                    onPressed: () { Navigator.pop(context); },
                    child: Text('뒤로'),
                  ),
                ) ,
                )
            ],
          ),
        ),
      ),
    );
  }
}

// 오더
Future<dynamic> boardInfoOrder(String order,dynamic param) async {
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
