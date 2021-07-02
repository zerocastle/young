import 'package:flutter/material.dart';

class MyPage extends StatefulWidget {
  const MyPage({ Key? key }) : super(key: key);

  @override
  _MyPageState createState() => _MyPageState();
}

class _MyPageState extends State<MyPage> {
  @override
  Widget build(BuildContext context) {
      return Scaffold(
      body: Container(
        child: Text('마이페이지라네'),
      ),
    );
  }
}