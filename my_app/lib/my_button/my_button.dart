import 'package:flutter/material.dart';

class MyButton extends StatelessWidget {

  final Widget image;
  final Widget text;
  final Color color;
  final double radius;
  final VoidCallback onPressed;
  final double fontSize;

  MyButton(
      {required this.image  ,
      required this.text,
      required this.color ,
      required this.radius,
      required this.onPressed,
      required this.fontSize});
  @override
  Widget build(BuildContext context) {
    return ButtonTheme(
      height: 50.0,
      child: ElevatedButton(
        child: Padding(
          padding: EdgeInsets.all(10.0),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              image,
              text,
              Opacity(
                opacity: 0.0,
                child: Image.asset('images/glogo.png'),
              ),
            ],
          ),
        ),
        style: ElevatedButton.styleFrom(
            textStyle: TextStyle(fontSize: fontSize), primary: color),
        onPressed: onPressed,
      ),
      shape: RoundedRectangleBorder(
        borderRadius: BorderRadius.all(
          Radius.circular(radius),
        ),
      ),
    );
  }
}
