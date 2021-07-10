
import 'package:flutter/material.dart';
import 'package:my_app/login_app/login.dart';

class RootPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    print('root_page created');
    return _handleCurrentScreen();
  }

  Widget _handleCurrentScreen() {
    return LogIn();
  }
}
