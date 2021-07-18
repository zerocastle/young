import 'package:carousel_slider/carousel_slider.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';
import 'package:my_app/data/network.dart';
import 'package:my_app/model/BoardVO.dart';

class Community extends StatefulWidget {
  final User user;
  const Community({Key? key, required this.user}) : super(key: key);

  @override
  _CommunityState createState() => _CommunityState();
}

final List<String> imgList = [];

class _CommunityState extends State<Community> {
  late Future<dynamic> _boards;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _boards = boardOrder('/board/getList');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SafeArea(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 10.0),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    '베스트',
                    style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                  ),
                  SizedBox(
                    height: 20.0,
                  ),
                  Column(
                    children: [
                      CarouselSlider(
                          items: [
                            Container(
                              width: 220,
                              child: Text('bannerCard 1'),
                              decoration: BoxDecoration(
                                color: Colors.teal[100],
                                borderRadius:
                                    BorderRadius.all(Radius.circular(10)),
                              ),
                            ),
                            Container(
                              width: 220,
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
                            viewportFraction: 0.6,
                            autoPlayInterval: const Duration(seconds: 4),
                            // aspectRatio: 16 / 2,
                          ))
                    ],
                  ),
                  SizedBox(
                    height: 30.0,
                  ),
                  Text(
                    '커뮤니티',
                    style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
                  ),
                  Row(
                    children: [
                      ElevatedButton(onPressed: () {}, child: Text('최신순')),
                      SizedBox(
                        width: 5,
                      ),
                      ElevatedButton(onPressed: () {}, child: Text('댓글 많은 순')),
                      SizedBox(
                        width: 5,
                      ),
                      ElevatedButton(onPressed: () {}, child: Text('좋아요 수'))
                    ],
                  ),
                ],
              ),
            ),
            getContent(),
            Expanded(child: getDataList(_boards))
          ],
        ),
      ),
    );
  }
}

FutureBuilder getDataList(Future<dynamic> _boards) {
  return FutureBuilder(
    future: _boards,
    builder: (BuildContext context, AsyncSnapshot snapshot) {
      if (!snapshot.hasData) return CircularProgressIndicator();
      return ListView.builder(
          padding: const EdgeInsets.all(8),
          itemCount: snapshot.data.length,
          itemBuilder: (context, index) {
            return Card(
              child: ListTile(
                onTap: () {},
                title: Text(snapshot.data[index]['btitle'].toString()),
              ),
            );
          });
    },
  );
}

Future<dynamic> boardOrder(String order) async {
  // String url = "http://localhost:3000/login";
  // String url = "http://192.168.15.4:3000/login";
  String url = "http://192.168.15.4:8181" + order;
  Network network = await Network(url);
  var data = await network.getJsonData();
  // Iterable l =data;
  // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
  // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
  return data;
}

// 컨테츠 함수
Widget getContent() {
  return Container(
    //  color: Colors.redAccent,
    height: 105,
    child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Divider(
          color: Colors.grey[850],
          thickness: 0.5,
        ),
        Text(
          'Board Title',
          style: TextStyle(fontWeight: FontWeight.w700, fontSize: 16),
        ),
        SizedBox(
          height: 5,
        ),
        Text(
            'Board Contents Board Contents Board Contents Board Contents Board Contents Board Contents'),
        // SizedBox(height: 8,),
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            // Text('Name | Time '),
            Text('Name  |  2 Hours ago '),
            Container(
              child: Row(
                children: [
                  Icon(Icons.remove_red_eye_outlined),
                  SizedBox(
                    width: 5,
                  ),
                  Text('32'),
                ],
              ),
            ),
          ],
        )
      ],
    ),
  );
}


// class bannerCard extends StatefulWidget {
//   const bannerCard({Key? key}) : super(key: key);

//   @override
//   _bannerCardState createState() => _bannerCardState();
// }

// class _bannerCardState extends State<bannerCard> {
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold();
//   }
// }


