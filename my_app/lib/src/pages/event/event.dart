import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/data/network.dart';
import 'package:my_app/model/BoardVO.dart';
class Event extends StatefulWidget {
  final User user;
  const Event({Key? key, required this.user}) : super(key: key);

  @override
  _EventState createState() => _EventState();
}

class _EventState extends State<Event> {
  // 펜션
  // 게시글
  late Future<dynamic> _boards;
  // 베스트 게시글
  late Future<dynamic> _bestBoards;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    // _boards = boardOrder('/board/getList');
    // _bestBoards = boardOrder('/board/bestList');
  }


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body:SafeArea(
        child: Padding(
          padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 5.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: [
              TextField(
                obscureText: true,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: '업체명을 검색하세요.'
                  ),
              ),
              Text('#핫검색어 #최고인기순위 #좋은업체이름'),
              SizedBox(height: 20,),
              Text('숙박'),
              Row(
                  children: [
                    ElevatedButton(onPressed: () {}, child: Text('펜션')),
                    SizedBox(
                      width: 5,
                    ),
                    ElevatedButton(onPressed: () {}, child: Text('호텔')),
                    SizedBox(
                      width: 5,
                    ),
                    ElevatedButton(onPressed: () {}, child: Text('모텔')),
                    SizedBox(
                      width: 5,
                    ),
                    ElevatedButton(onPressed: () {}, child: Text('게스트하우스')),
                  ],
                ),
              Container(
                width: 120,
                height: 120,
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      margin: const EdgeInsets.only(top: 5.0),
                      child: Image(image: AssetImage('assets/fire.png'),),
                    ),
                    SizedBox(width: 5,),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            ElevatedButton(onPressed: () {}, child: Text('숙박'), ),   
                            SizedBox(width: 5,),
                            ElevatedButton(onPressed: () {}, child: Text('호텔'), ),
                          ]
                        ,),
                        Text('업체이름'),
                        Row(
                          children: [
                            Icon(
                              Icons.favorite,
                              color: Colors.pink,
                              size: 12.0,
                            ),
                            Text('4.0 | 서울강남구')
                          ],
                        ),
                        Text('95,500원 최대80%'),
                      ],
                    ),
                   
                  ]
                )
              ),
               Container(
                width: 120,
                height: 120,
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      margin: const EdgeInsets.only(top: 5.0),
                      child: Image(image: AssetImage('assets/fire.png'),),
                    ),
                    SizedBox(width: 5,),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            ElevatedButton(onPressed: () {}, child: Text('숙박'), ),   
                            SizedBox(width: 5,),
                            ElevatedButton(onPressed: () {}, child: Text('호텔'), ),
                          ]
                        ,),
                        Text('업체이름'),
                        Row(
                          children: [
                            Icon(
                              Icons.favorite,
                              color: Colors.pink,
                              size: 12.0,
                            ),
                            Text('4.0 | 서울강남구')
                          ],
                        ),
                        Text('95,500원 최대80%'),
                      ],
                    ),
                   
                  ]
                )
              ),
               Container(
                width: 120,
                height: 120,
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Container(
                      margin: const EdgeInsets.only(top: 5.0),
                      child: Image(image: AssetImage('assets/fire.png'),),
                    ),
                    SizedBox(width: 5,),
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Row(
                          children: [
                            ElevatedButton(onPressed: () {}, child: Text('숙박'), ),   
                            SizedBox(width: 5,),
                            ElevatedButton(onPressed: () {}, child: Text('호텔'), ),
                          ]
                        ,),
                        Text('업체이름'),
                        Row(
                          children: [
                            Icon(
                              Icons.favorite,
                              color: Colors.pink,
                              size: 12.0,
                            ),
                            Text('4.0 | 서울강남구')
                          ],
                        ),
                        Text('95,500원 최대80%'),
                      ],
                    ),
                   
                  ]
                )
              ),
            ],
          ),
        ),
      ),
    );
  }
}


// 오더
Future<dynamic> boardOrder(String order) async {
  // String url = "http://localhost:3000/login";
  // String url = "http://192.168.15.4:3000/login";
  // String url = "http://192.168.15.4:8181" + order;
  String url = "http://192.168.219.109:8080" + order;
  Network network =  Network(url);
  var data = await network.getJsonData();
  print("data :::$data");
  // Iterable l =data;
  // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
  // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
  return data;
}
