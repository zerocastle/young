import 'package:flutter/material.dart';

class Community extends StatefulWidget {
  const Community({ Key? key }) : super(key: key);

  @override
  _CommunityState createState() => _CommunityState();
}

class _CommunityState extends State<Community> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('커뮤니티',
        ),
        centerTitle: true,
        actions: [
          IconButton(
            icon: Icon(Icons.menu),
            onPressed: (){
              print('menu icon clicked');
            }, 
          )
        ],
      ),
      body: 
      Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text('베스트'),
            Text('커뮤니티'),
          ],
        ),
      ),
    );
  }
}