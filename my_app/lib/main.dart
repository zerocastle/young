import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:my_app/root_page.dart';

void main() async{
  WidgetsFlutterBinding.ensureInitialized();
   await Firebase.initializeApp();
   runApp(MyApp());
}

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


