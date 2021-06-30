import 'package:flutter/material.dart';
// import 'package:my_app/src/app.dart';
import 'package:my_app/src/pages/community.dart';
void main() => runApp(MyApp());


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    var green = Colors.blue;
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'gunDabang',
      theme: ThemeData(
        primarySwatch: green
      ),
      // home: MyHomePage(title: '군다방'),
      initialRoute: '/',
      routes: {
        '/'  : (context) => MyHomePage(title: '군다방'),
        '/community' : (context) => Community(),
      },
    );
  }
}

class MyHomePage extends StatelessWidget {
  final String title;
  const MyHomePage({ Key? key , required this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(this.title),
        backgroundColor: Colors.green,
      ),
      bottomNavigationBar: BottomNavigationBar(
        type: BottomNavigationBarType.fixed,
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
            icon: IconButton(
              onPressed: (){
                Navigator.pushNamed(context, '/community');
              }, 
              icon: Icon(Icons.keyboard),
              
            )
            ),
            BottomNavigationBarItem(
            label: '마이페이지',
            icon: Icon(Icons.person))
        ],
      ),
    );
  }
}
