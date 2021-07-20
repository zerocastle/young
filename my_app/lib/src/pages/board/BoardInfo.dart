import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class BoardInfo extends StatefulWidget {
  final dynamic param;
  // final dynamic param;
  const BoardInfo({Key? key, this.param}) : super(key: key);

  @override
  _BoardInfoState createState() => _BoardInfoState();
}

class _BoardInfoState extends State<BoardInfo> {
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
