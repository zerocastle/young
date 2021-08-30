import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

// import kakao sdk
import 'package:kakao_flutter_sdk/user.dart';

class LoginResult extends StatefulWidget {
  @override
  _LoginResultState createState() => _LoginResultState();
}

class _LoginResultState extends State<LoginResult> {
  @override
  void dispose() {
    super.dispose();
  }

  @override
  void initState() {
    super.initState();
    _initTexts();
  }

  _initTexts() async {
    final User user = await UserApi.instance.me();

    print(
        "=========================[kakao account]=================================");
    print(user.kakaoAccount.toString());
    print(
        "=========================[kakao account]=================================");

    setState(() {
      
      _accountNickname = user.kakaoAccount!.profile!.nickname.toString();
      _accountThumbnailImageUrl = user.kakaoAccount!.profile!.thumbnailImageUrl.toString();
      // _accountEmail = user.kakaoAccount!.email.toString() ;
      // _ageRange = user.kakaoAccount!.ageRange.toString();
      // _gender = user.kakaoAccount!.gender.toString();
    });
  }

  String _accountNickname = 'None';
  String _accountThumbnailImageUrl = 'None';
  // String _accountEmail = 'None';
  // String _ageRange = 'None';
  // String _gender = 'None';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Center(
          child: Column(
            children: [
              Text(_accountNickname),
              CircleAvatar(
                backgroundImage: NetworkImage(_accountThumbnailImageUrl),
                radius: 60.0,
              ),
              // Text(_gender),
            ],
          ),
        ),
      ),
    );
  }
}