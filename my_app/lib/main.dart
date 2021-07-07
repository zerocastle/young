import 'package:flutter/material.dart';
import 'package:my_app/root_page.dart';
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
      home: RootPage(),
    );
  }
}


