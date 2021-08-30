import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// Import kakao sdk
import 'package:kakao_flutter_sdk/auth.dart';
import 'package:kakao_flutter_sdk/user.dart';
import 'package:kakao_flutter_sdk/common.dart';

import 'package:my_app/login_app/login_result.dart';

class LoginScreen extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _LoginState();
  }
}

class _LoginState extends State<LoginScreen> {
  @override
  void initState() {
    super.initState();
    _initKakaoTalkInstalled();
  }

  @override
  void dispose() {
    super.dispose();
  }

  _initKakaoTalkInstalled() async {
    final installed = await isKakaoTalkInstalled();
    setState(() {
      _isKakaoTalkInstalled = installed;
    });
  }

  bool _isKakaoTalkInstalled = true;

  _issueAccessToken(String authCode) async {
    try {
      var token = await AuthApi.instance.issueAccessToken(authCode);
      AccessTokenStore.instance.toStore(token);
        print("Navigator _issueAccessToken");
      // Navigator.pushNamed(context, '/login_result');
         Navigator.push(context,
                MaterialPageRoute(builder: (context) => LoginResult()));
    } catch (e) {
      print("error on issuing access token: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    // KaKao native app key
  	KakaoContext.clientId = "6e78fcb7a7763bd07cff27b7d43f7ae0";
    // KaKao javascript key
	KakaoContext.javascriptClientId = "68ecc10f822c919e14b485a198bc5ecd";
    
    isKakaoTalkInstalled();
    
    return Scaffold(
      appBar: AppBar(
        title: Text("Kakao Flutter SDK Login"),
        actions: [],
      ),
      body: Center(
          child: Column(
        children: <Widget>[
          RaisedButton(child: Text("Login"), onPressed: _loginWithKakao),
          RaisedButton(
              child: Text("Login with Talk"),
              onPressed: _isKakaoTalkInstalled ? _loginWithTalk : null),
        ],
      )),
    );
  }

  _loginWithKakao() async {
    try {
      var code = await AuthCodeClient.instance.request();
      await _issueAccessToken(code);
    } catch (e) {
      print(e);
    }
  }

  _loginWithTalk() async {
    try {
      var code = await AuthCodeClient.instance.requestWithTalk();
      await _issueAccessToken(code);
    } catch (e) {
      print(e);
    }
  }
}
