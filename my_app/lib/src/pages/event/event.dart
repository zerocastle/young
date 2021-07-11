import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Event extends StatefulWidget {
  final User user;
  const Event({Key? key, required this.user}) : super(key: key);

  @override
  _EventState createState() => _EventState();
}

class _EventState extends State<Event> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Text('이벤트라네'),
      ),
    );
  }
}
