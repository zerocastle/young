
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
  const MyHomePage({required this.title,required this.user});

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _selectedIndex = 0;
  final Color? bottomMainColor = Colors.green[400]; 
  final List<Widget> _children = [Home(),Shopping(),Event(),Community(),MyPage()];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(this.widget.title),
        backgroundColor: Colors.green,
      ),
      body: _children[_selectedIndex],
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
        backgroundColor: bottomMainColor,
        selectedItemColor: Colors.white,
        unselectedItemColor: Colors.white.withOpacity(.60),
        selectedFontSize : 14,
        unselectedFontSize: 14,
        currentIndex: _selectedIndex,
        onTap: (int index){
          setState(() {
            _selectedIndex = index;
          });
        }, 
        items: [
          BottomNavigationBarItem(
            label: '홈',
            icon: Icon(Icons.home)),
            BottomNavigationBarItem(
            label: '쇼핑',
            icon: Icon(Icons.shopping_bag)),
            BottomNavigationBarItem(
            label: '이벤트',
            icon: Icon(Icons.emoji_events)),
            BottomNavigationBarItem(
            label: '게시글',
            icon: Icon(Icons.keyboard)),
            BottomNavigationBarItem(
            label: '마이페이지',
            icon: Icon(Icons.person))
        ],
      ),
    );
  }
}