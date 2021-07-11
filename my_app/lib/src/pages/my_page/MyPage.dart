import 'package:flutter/material.dart';
import 'package:my_app/common/common_widget.dart';

class MyPage extends StatefulWidget {
  const MyPage({Key? key}) : super(key: key);

  @override
  _MyPageState createState() => _MyPageState();
}

class _MyPageState extends State<MyPage> {

  // 공통 위젯 쓸려고 놔둠
  CommonWidget commonWidget = CommonWidget();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Padding(
      padding: EdgeInsets.fromLTRB(30.0, 40.0, 0.0, 0.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Center(
            child: CircleAvatar(
              backgroundImage: AssetImage('assets/fire.png'),
              radius: 60.0,
            ),
          ),
          commonWidget.getDivider(),
          Row(
            children: <Widget>[
              Column(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  IconButton(
                    tooltip: '포인트로 페이지로 이동',
                    icon: const Icon(Icons.point_of_sale_sharp),
                    onPressed: () {},
                  ),
                  Text('포인트'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
              Column(
                children: [
                  IconButton(
                    tooltip: '포인트로 페이지로 이동',
                    icon: const Icon(Icons.point_of_sale_sharp),
                    onPressed: () {},
                  ),
                  Text('포인트'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
              Column(
                children: [
                  IconButton(
                    tooltip: '포인트로 페이지로 이동',
                    icon: const Icon(Icons.point_of_sale_sharp),
                    onPressed: () {},
                  ),
                  Text('포인트'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
              Column(
                children: [
                  IconButton(
                    tooltip: '포인트로 페이지로 이동',
                    icon: const Icon(Icons.point_of_sale_sharp),
                    onPressed: () {},
                  ),
                  Text('포인트'),
                ],
              ),
            ],
          )
        ],
      ),
    ));
  }



}
