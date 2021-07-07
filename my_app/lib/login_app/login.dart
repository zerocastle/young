
import 'package:flutter/material.dart';
import 'package:my_app/MyHomePage.dart';
import 'package:my_app/my_button/my_button.dart';

class LogIn extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.blue,
        title: Text(
          'Sign In',
          style: TextStyle(color: Colors.white),
        ),
        centerTitle: true,
        elevation: 0.2,
      ),
      body: _buildButton(context),
    );
  }

  Widget _buildButton(context) {
    return Padding(
      padding: EdgeInsets.all(16.0),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          MyButton(
            image: Image.asset('images/glogo.png'),
            text: Text(
              'Login with Google',
              style: TextStyle(color: Colors.black87, fontSize: 15.0),
            ),
            color: Colors.white,
            radius: 4.0,
            onPressed: () {
              Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => MyHomePage(title: '군다방에 오신걸 환영합니다.',)),
                );
            },
            fontSize: 15.0,
          ),
          SizedBox(
            height: 10.0,
          ),
          MyButton(
            image: Image.asset('images/flogo.png'),
            text: Text(
              'Login with Facebook',
              style: TextStyle(color: Colors.black87, fontSize: 15.0),
            ),
            color: Color(0XFF334D92),
            radius: 4.0,
            onPressed: () {},
            fontSize: 15.0,
          ),
          SizedBox(
            height: 10.0,
          ),
          MyButton(
            image: Icon(Icons.mail),
            text: Text(
              'email with login',
              style: TextStyle(color: Colors.black87, fontSize: 15.0),
            ),
            color: Colors.green,
            radius: 4.0,
            onPressed: () {},
            fontSize: 15.0,
          ),
          Opacity(
              opacity: 0.0,
              child: Icon(
                Icons.mail,
                color: Colors.white,
              )),
          SizedBox(
            height: 10.0,
          ),
        ],
      ),
    );
  }
}
