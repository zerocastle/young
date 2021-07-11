import 'package:carousel_slider/carousel_slider.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Community extends StatefulWidget {
  final User user;
  const Community({Key? key, required this.user}) : super(key: key);

  @override
  _CommunityState createState() => _CommunityState();
}

final List<String> imgList = [];

class _CommunityState extends State<Community> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      // appBar: AppBar(
      //   title: Text('커뮤니티',
      //   ),
      //   centerTitle: true,
      //   actions: [
      //     IconButton(
      //       icon: Icon(Icons.menu),
      //       onPressed: (){
      //         print('menu icon clicked');
      //       },
      //     )
      //   ],
      // ),
      body: SafeArea(
        child: ListView(
          children: [
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
                  //  ListView(
                  //    children: [
                  Container(
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
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
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
                  ),
                  Container(
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
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
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
                  ),
                  Container(
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
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
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
                  ),
                  Container(
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
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
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
                  ),
                  Container(
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
                          style: TextStyle(
                              fontWeight: FontWeight.w700, fontSize: 16),
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
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}

class bannerCard extends StatefulWidget {
  const bannerCard({Key? key}) : super(key: key);

  @override
  _bannerCardState createState() => _bannerCardState();
}

class _bannerCardState extends State<bannerCard> {
  @override
  Widget build(BuildContext context) {
    return Scaffold();
  }
}
