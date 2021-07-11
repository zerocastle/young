import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/common/common_widget.dart';

class MyPage extends StatefulWidget {

  final User user;

  const MyPage({Key? key, required this.user}) : super(key: key);

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
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              Column(
                // mainAxisAlignment: MainAxisAlignment.spaceEvenly,
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
                    tooltip: '쿠폰 페이지로 이동',
                    icon: const Icon(Icons.money_sharp),
                    onPressed: () {},
                  ),
                  Text('쿠폰'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
              Column(
                children: [
                  IconButton(
                    tooltip: '주문 목록으로 이동',
                    icon: const Icon(Icons.list),
                    onPressed: () {},
                  ),
                  Text('주문목록'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
              Column(
                children: [
                  IconButton(
                    tooltip: '환경설정',
                    icon: const Icon(Icons.engineering),
                    onPressed: () {},
                  ),
                  Text('환경설정'),
                ],
              ),
              commonWidget.getSizedBox(30.0, 0.0),
            ],
          ),
          commonWidget.getDivider(),
          getListView(),
        ],
      ),
    ));
  }

  // 내정보 기능 위젯
  Widget getListView() {
    return Column(
      children: [
        ListTile(
          title: Text('내가 작성한 글'),
          onTap: () {
            print('내가 작성한 글');
          },
          trailing : Icon(Icons.arrow_right)
        ),
        ListTile(
          title: Text('내가 작성한 댓글'),
          onTap: () {
            print('내가 작성한 댓글');
          },
          trailing : Icon(Icons.arrow_right)
        ),
        ListTile(
          title: Text('로그아웃'),
          onTap: () {
            print('logout is clicked');
          },
          trailing : Icon(Icons.arrow_right)
        ),
      ],
    );
  }
}
