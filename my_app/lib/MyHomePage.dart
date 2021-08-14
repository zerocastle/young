import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:my_app/src/controller/community_controller.dart';
import 'package:my_app/src/pages/board/Community.dart';
import 'package:my_app/src/pages/event/event.dart';
import 'package:my_app/src/pages/home/home.dart';
import 'package:my_app/src/pages/my_page/MyPage.dart';
import 'package:my_app/src/pages/shopping/Shopping.dart';

class MyHomePage extends StatefulWidget {
  // final user = FirebaseAuth.instance.authStateChanges();
  final User user;
  const MyHomePage({Key? key, required this.user})
      : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}
class _MyHomePageState extends State<MyHomePage> {

  late User _user;
  final GoogleSignIn _googleSignIn = GoogleSignIn();

  int _selectedIndex = 0;
  final Color? bottomMainColor = Colors.green;

  // 페이지들
  List<Widget>? _children;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _user = widget.user;
    print('here 생성자$_user');
    print(_user.email);

    _children = [
      Home(user: this._user),
      Shopping(user: this._user),
      Event(user: this._user),
      Community(user: this._user),
      MyPage(user: this._user)
    ];

  }
  

  @override
  Widget build(BuildContext context) {
    
    return Scaffold(
        body: _children![_selectedIndex],
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

  Widget cateBtn(cate) {
    return SizedBox(
          child:  ElevatedButton(
            onPressed: () {},
            child: Text(cate, style: TextStyle(fontSize: 12),),
          ),
        );
  }

  // 드로워 위젯 붙이기
  Widget _getDrawer() {
    var nickName = _user.displayName;
    var email = _user.email;
    var photoURL = _user.photoURL;
    return Drawer(
      child: ListView(
        padding: EdgeInsets.zero,
        children: <Widget>[
          Center(
            child: UserAccountsDrawerHeader(
              currentAccountPicture: CircleAvatar(
                backgroundImage: NetworkImage(photoURL!),
                backgroundColor: Colors.white,
              ),
              accountEmail: Text(email!),
              accountName: Text(nickName!),
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
              FirebaseAuth.instance.signOut();
              _googleSignIn.signOut();
            },
          )
        ],
      ),
    );
  }
}
