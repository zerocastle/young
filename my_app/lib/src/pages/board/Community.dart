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
      body: SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text('베스트'),
              SizedBox(
                height: 20.0,
              ),
              Row(
                children: [
                  Container(
                    width: 200,
                    height: 140,
                    child: Text('bannerCard 1'),
                    decoration: BoxDecoration(
                      color: Colors.teal[100],
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      // border: Border.all(color: Colors.black, width: 3),
                    ),
                  ),
                  SizedBox(
                    width: 20.0,
                  ),
                  Container(
                    width: 170,
                    height: 140,
                    child: Text('bannerCard 2'),
                    decoration: BoxDecoration(
                      color: Colors.teal[100],
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      // border: Border.all(color: Colors.black, width: 3),
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: 30.0,
              ),
              Text('커뮤니티'),
              Row(
                children: [
                  TextButton(
                      onPressed: (){},
                      child: Text('최신순')
                  ),
                  TextButton(
                      onPressed: (){},
                      child: Text('댓글 많은 순',
                      style: TextStyle(
                        backgroundColor: Colors.black,
                        color: Colors.white,
                      ),

                      )

                  ),
                  TextButton(
                      onPressed: (){},
                      child: Text('좋아요 수')
                  )
                ],
              ),

              Divider(
                height: 60.0,
                color: Colors.grey[850],
                thickness: 0.5,
                // indent: 20.0,
                // endIndent: 20.0,
              ),
              Text('2021년 장마기간'),
            ],
          ),
        ),
      ),
    );
  }
}

class bannerCard extends StatefulWidget {
  const bannerCard({ Key? key }) : super(key: key);

  @override
  _bannerCardState createState() => _bannerCardState();
}

class _bannerCardState extends State<bannerCard> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
    );
  }
}