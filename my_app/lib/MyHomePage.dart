import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/src/pages/board/Community.dart';
import 'package:my_app/src/pages/event/event.dart';
import 'package:my_app/src/pages/home/home.dart';
import 'package:my_app/src/pages/my_page/MyPage.dart';
import 'package:my_app/src/pages/shopping/Shopping.dart';

class MyHomePage extends StatefulWidget {
  final String title;
  // final user = FirebaseAuth.instance.authStateChanges();
  final User user;
  const MyHomePage({Key? key, required this.title, required this.user})
      : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  late User _user;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _user = widget.user;
    print('herer 생성자$_user');
    print(_user.email);
  }

  int _selectedIndex = 0;
  final Color? bottomMainColor = Colors.green;
  final List<Widget> _children = [
    Home(),
    Shopping(),
    Event(),
    Community(),
    MyPage()
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(this.widget.title),
          backgroundColor: Colors.green,
          centerTitle: true,
        ),
        body: _children[_selectedIndex],
        bottomNavigationBar: BottomNavigationBar(
          type: BottomNavigationBarType.fixed,
          backgroundColor: bottomMainColor,
          selectedItemColor: Colors.white,
          unselectedItemColor: Colors.white.withOpacity(.60),
          selectedFontSize: 14,
          unselectedFontSize: 14,
          currentIndex: _selectedIndex,
          onTap: (int index) {
            setState(() {
              _selectedIndex = index;
            });
          },
          items: [
            BottomNavigationBarItem(label: '홈', icon: Icon(Icons.home)),
            BottomNavigationBarItem(
                label: '쇼핑', icon: Icon(Icons.shopping_bag)),
            BottomNavigationBarItem(
                label: '이벤트', icon: Icon(Icons.emoji_events)),
            BottomNavigationBarItem(label: '게시글', icon: Icon(Icons.keyboard)),
            BottomNavigationBarItem(label: '마이페이지', icon: Icon(Icons.person))
          ],
        ),
        drawer: _getDrawer());
  }

  // 드로워 위젯 붙이기
  Widget _getDrawer() {
    var nickName = _user.displayName;
    var email = _user.email;
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          Center(
            child: UserAccountsDrawerHeader(
              currentAccountPicture: CircleAvatar(
                backgroundImage: AssetImage('assets/fire.png'),
                backgroundColor: Colors.white,
              ),
              accountEmail: Text('123'),
              accountName: Text('oo'),
              onDetailsPressed: () {
                print('arrow is clicked');
              },
              decoration: BoxDecoration(
                color: Colors.green,
              ),
            ),
          ),
          ListTile(
            leading: Icon(
              Icons.logout,
              color: Colors.grey[850],
            ),
            title: Text('로그아웃'),
            onTap: () {
              print('logout is clicked');
            },
          )
        ],
      ),
    );
  }
}
