import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/MyHomePage.dart';
import 'package:my_app/loading_page.dart';
import 'package:my_app/login_app/login.dart';

class RootPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print('root_page created');
    return _handleCurrentScreen();
  }

  Widget _handleCurrentScreen() {
    final instance = FirebaseAuth.instance;
    final user = instance.currentUser;

    return StreamBuilder(
        stream: instance.authStateChanges(),
        builder: (BuildContext context, AsyncSnapshot snapshot) {
          // 연결 상태 체크
          if (snapshot.connectionState == ConnectionState.waiting) {
            return LoadingPage();
          }else{
            if(snapshot.hasData){
              //print(snapshot.data);
              return MyHomePage(user : snapshot.data);
            }else{
              return LogIn();
            }
          }
        });
  }
}
