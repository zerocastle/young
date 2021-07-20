// import 'package:my_app/src/app.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/data/network.dart';
import 'package:http/http.dart' as http;
import 'package:charts_flutter/flutter.dart' as charts;
import 'package:carousel_slider/carousel_slider.dart';

final List titles = [
  ' Coffee ',
  ' Bread ',
];
 int _current = 0;
class Home extends StatefulWidget {
  final User user;

  const Home({Key? key, required this.user}) : super(key: key);

  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //testData();
  }

  void testData() async {

    // String url = "http://localhost:3000/login";
    // String url = "http://192.168.15.4:3000/login";
    String url = "http://192.168.15.4:8181/member/getList";
    Network network = await Network(url);
    var data = await network.getJsonData();
    print(data);
    print(data[0]['email']);

  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      // backgroundColor: Colors.grey[100],
      body: SafeArea(
        child: ListView(
          children: <Widget>[
            Padding(
            padding: const EdgeInsets.symmetric(horizontal: 10.0),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                Text('육군 상병'),
                Text('영삼이 님의 복무일수 입니다.',
                style: TextStyle(
                  fontWeight: FontWeight.w700,
                  fontSize: 23,
                  ),
                ),
                SizedBox(height: 30,),
                Container(
                    child: Column(
                      children: <Widget>[
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                          children: [
                            Container(
                              child: Column(
                                children: [
                                  Text('전체복무일'),
                                  Text('600일',
                                    style: TextStyle(
                                      fontWeight: FontWeight.w700
                                    ),
                                  ),
                                ],
                              )
                            ),
                            Container(
                              width: 1,
                              height: 35,
                              child: VerticalDivider(
                                color: Colors.grey[300],
                                thickness: 2.0,
                                ),
                            ),
                            Container(
                              child: Column(
                                children: [
                                  Text('현재복무일'),
                                  Text('100일',
                                    style: TextStyle(
                                      fontWeight: FontWeight.w700
                                    ),
                                  ),
                                ],
                              )
                            ),
                            Container(
                              width: 1,
                              height: 35,
                              child: VerticalDivider(
                                color: Colors.grey[300],
                                thickness: 2.0,
                                ),
                            ),
                            Container(
                              child: Column(
                                children: [
                                  Text('남은복무일'),
                                  Text('500일',
                                    style: TextStyle(
                                      fontWeight: FontWeight.w700
                                    ),
                                  ),
                                ],
                              )
                            ),
                          ],
                        ),
                      ],
                    ),
                ),
                SizedBox(height: 50,),
                Text("전역",
                  style: TextStyle(
                    fontWeight: FontWeight.w700
                  ),
                ),
                Container(
                    margin: EdgeInsets.symmetric(vertical: 5),
                    width: 300,
                    height: 20,
                    child: ClipRRect(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      child: LinearProgressIndicator(
                        value: 0.2,
                        backgroundColor: Colors.grey[300],
                        color: Colors.black
                      ),
                    )
                  ),
                Container(
                  alignment: Alignment.topRight,
                  child: Text.rich(TextSpan
                    (
                      // text: '남은 복무일 500일 남았습니다.',
                      children: <TextSpan>[
                        TextSpan(text: '남은 복무일 '),
                        TextSpan(text: '500일 ', style: TextStyle(fontWeight: FontWeight.w600)),
                        TextSpan(text: '남았습니다.'),
                      ]
                    )
                  )
                ),
                Text("병장 진급",
                  style: TextStyle(
                    fontWeight: FontWeight.w700
                  ),
                ),
                Container(
                    margin: EdgeInsets.symmetric(vertical: 5),
                    width: 300,
                    height: 20,
                    child: ClipRRect(
                      borderRadius: BorderRadius.all(Radius.circular(10)),
                      child: LinearProgressIndicator(
                        value: 0.8,
                        backgroundColor: Colors.grey[300],
                        color: Colors.black
                      ),
                    )
                  ),
                Container(
                  alignment: Alignment.topRight,
                  child: Text.rich(TextSpan
                    (
                      // text: '남은 복무일 500일 남았습니다.',
                      children: <TextSpan>[
                        TextSpan(text: '진급까지 '),
                        TextSpan(text: 'n일 ', style: TextStyle(fontWeight: FontWeight.w600)),
                        TextSpan(text: '남았습니다.'),
                      ]
                    )
                  )
                ),
                SizedBox(height: 50,),
                Container(
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text('식단표',
                          style: TextStyle(
                            fontWeight: FontWeight.w600,
                            fontSize: 20,
                          ),
                      ),
                      Text('더보기'),
                    ],
                  ),
                ),
                SizedBox(height: 20,),
                Container(
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.all(Radius.circular(10)),
                    boxShadow: [
                      BoxShadow(
                        color: Colors.grey.shade400.withOpacity(0.5),
                        spreadRadius: 5,
                        blurRadius: 10,
                        offset: Offset(0, 3),
                      )
                    ]
                  ),
                  child: Padding(
                    padding: const EdgeInsets.symmetric(vertical: 15.0, horizontal: 20.0),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.stretch,
                      children: [
                        Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Container(
                              child: Row(
                                children: [
                                  Text('아침', style: TextStyle(fontSize: 15, fontWeight: FontWeight.w400),),
                                  SizedBox(width: 10,),
                                  Text('점심', style: TextStyle(fontSize: 15, fontWeight: FontWeight.w400),),
                                  SizedBox(width: 10,),
                                  Text('저녁', style: TextStyle(fontSize: 15, fontWeight: FontWeight.w400),),
                                ],
                              ),
                            ),
                            Container(
                              child: Text('2021.06.20', style: TextStyle(fontSize: 15, fontWeight: FontWeight.w400),),
                            )
                            
                          ],
                        ),
                        SizedBox(height: 10,),
                        Text('현미밥'),
                        Text('감자계란국'),
                        Text('가지볶음'),
                        Text('두부조림'),
                        Text('감자반'),
                        Text('배추김치'),
                        Text('우유(200ML, 연간)'),
                      ],
                    ),
                  ),
                ),
                SizedBox(height: 50,),
                 CarouselSlider(
                  items: [
                    Container(
                      width: 330,
                      child: Text('bannerCard 1'),
                      decoration: BoxDecoration(
                        color: Colors.grey[200],
                        borderRadius:
                            BorderRadius.all(Radius.circular(10)),
                      ),
                    ),
                    Container(
                      width: 330,
                      child: Text('bannerCard 2'),
                      decoration: BoxDecoration(
                        color: Colors.teal[100],
                        borderRadius:
                            BorderRadius.all(Radius.circular(10)),
                        // border: Border.all(color: Colors.black, width: 3),
                      ),
                    ),
                  ],
                  options: CarouselOptions(
                    autoPlay: true,
                    height: 120,
                    disableCenter: false,
                    viewportFraction: 0.9,
                    autoPlayInterval: const Duration(seconds: 4),
                    // aspectRatio: 16 / 2,
                  )
                ),
                SizedBox(height: 50,),
              ],
            ),
          ),
        ]),
      ),
    );
  }
}
