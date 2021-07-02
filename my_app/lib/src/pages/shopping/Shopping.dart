import 'package:flutter/material.dart';

class Shopping extends StatefulWidget {
  const Shopping({ Key? key }) : super(key: key);

  @override
  _ShoppingState createState() => _ShoppingState();
}

class _ShoppingState extends State<Shopping> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Text('쇼핑아라네'),
      ),
    );
  }
}