import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:flutter_speed_dial/flutter_speed_dial.dart';
import 'package:intl/intl.dart';
import 'package:my_app/model/ShopVO.dart';
import 'package:my_app/src/controller/shopping_controller.dart';
import 'package:get/get.dart';

class Shopping extends StatefulWidget {
  final User? user;
  const Shopping({Key? key, this.user}) : super(key: key);

  @override
  _ShoppingState createState() => _ShoppingState();
}

class _ShoppingState extends State<Shopping> {
  late ShoppingController controller;

  @override
  void initState() {
    // TODO: implement initState
    controller = Get.put(ShoppingController());
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    // Get.put(ShoppingController());
    return Scaffold(
      body: SafeArea(
        child: Padding(
            padding: const EdgeInsets.all(0.0),
            child: Obx(() => CustomScrollView(
                  controller: controller.scrollController,
                  slivers: <Widget>[
                    SliverAppBar(
                      title: Text('스토어'),
                      floating: true,
                    ),
                    SliverToBoxAdapter(
                      child: Padding(
                        padding: const EdgeInsets.all(16.0),
                        child: Column(
                          children: [
                            Container(
                              child: TextField(
                                decoration: InputDecoration(
                                    border: InputBorder.none,
                                    labelText: "업체명을 검색하세요.",
                                    contentPadding:
                                        EdgeInsets.symmetric(horizontal: 20.0),
                                    fillColor: Colors.grey[300],
                                    filled: true,
                                    suffixIcon: Icon(Icons.search)),
                              ),
                              decoration: BoxDecoration(
                                  //  color: Colors.grey[300],
                                  border:
                                      Border.all(width: 0, color: Colors.grey)),
                            ),
                            SizedBox(
                              height: 20,
                            ),
                            Container(
                              height: 30.0,
                              child: ListView(
                                scrollDirection: Axis.horizontal,
                                children: [
                                  Container(
                                      child: Text(
                                    'BEST MATCH',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  )),
                                  SizedBox(
                                    width: 15,
                                  ),
                                  Container(
                                      child: Text(
                                    'TOP RATED',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  )),
                                  SizedBox(
                                    width: 15,
                                  ),
                                  Container(
                                      child: Text(
                                    'PRICE LOW-HIGH',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  )),
                                  SizedBox(
                                    width: 15,
                                  ),
                                  Container(
                                      child: Text(
                                    'PRICE HIGH-LOW',
                                    style: TextStyle(
                                      fontSize: 16,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ))
                                ],
                              ),
                            ),
                            Divider(
                              color: Colors.grey[300],
                              thickness: 2.0,
                            ),
                            Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                Row(
                                  children: [
                                    Text('전체 ',
                                        style: TextStyle(
                                            fontWeight: FontWeight.w600,
                                            fontSize: 16)),
                                    Text('100',
                                        style: TextStyle(
                                            fontWeight: FontWeight.w600,
                                            fontSize: 16,
                                            color: Colors.grey[600]))
                                  ],
                                ),
                                Row(
                                  children: [
                                    Icon(
                                      Icons.shuffle,
                                      size: 16,
                                    ),
                                    Text(' 추천순',
                                        style: TextStyle(
                                            fontWeight: FontWeight.w600,
                                            fontSize: 16)),
                                  ],
                                ),
                              ],
                            ),
                          ],
                        ),
                      ),
                    ),
                    SliverPadding(
                        padding: const EdgeInsets.all(0.0),
                        sliver: _getInfoShop())
                  ],
                ))
            // Text('쇼핑아라네'),
            ),
      ),
      floatingActionButton: SpeedDial(
          icon: Icons.plus_one,
          backgroundColor: Colors.blueAccent,
          children: [
            SpeedDialChild(
              child: Icon(Icons.create),
              label: '신규작성',
              backgroundColor: Colors.greenAccent,
              onTap: () {
                Get.toNamed("/boardWrite");
              },
            ),
            SpeedDialChild(
              child: Icon(Icons.refresh),
              label: '세로고침',
              backgroundColor: Colors.greenAccent,
              onTap: () {/* Do something */},
            ),
            // SpeedDialChild(
            //   child: Icon(Icons.chat),
            //   label: 'Message',
            //   backgroundColor: Colors.amberAccent,
            //   onTap: () {/* Do something */},
            // ),
          ]),
    );
  }

  /************************************************************************* start 베스트 컴포넌트********************************************************************************** */

  int _hasMore() {
    // ignore: unrelated_type_equality_checks
    if (controller.hasMore == true) {
      return 1;
    } else {
      return 0;
    }
  }

  Widget _getInfoShop() {
    var size = MediaQuery.of(context).size;
    final double itemHeight = 350.0;
    final double itemWidth = size.width / 2;
    return SliverList(
        delegate: SliverChildBuilderDelegate(
      (context, index) {
        ShopVO vo = controller.shopResult.value.items![index];
        ShopVO nextVO = controller.shopResult.value.items![index + 1];
        // ignore: unrelated_type_equality_checks
        if (index == controller.shopResult.value.items!.length) {
          return Center(
              child: Padding(
            padding: const EdgeInsets.only(top: 15),
            child: CircularProgressIndicator(),
          ));
        }

        return Container(
          child: Column(
            children: [
              ListTile(
                  title: GridView.count(
                primary: false,
                padding: const EdgeInsets.all(10.0),
                crossAxisSpacing: 10.0,
                mainAxisSpacing: 10.0,
                crossAxisCount: 2,
                childAspectRatio: (itemWidth / itemHeight),
                shrinkWrap: true,
                children: [
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Expanded(
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(15.0),
                          child: Container(color: Colors.grey[300]),
                        ),
                      ),
                      Text(
                          NumberFormat.currency(
                                  locale: "ko_KR",
                                  symbol: "￦",
                                  decimalDigits: 0)
                              .format(int.parse(vo.price!)),
                          style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.w700,
                          )),
                      SizedBox(
                        height: 15,
                      ),
                      Text(vo.subtitle.toString(),
                          style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.w700,
                          )),
                      RichText(
                        overflow: TextOverflow.ellipsis,
                        strutStyle: StrutStyle(fontSize: 14.0),
                        text: TextSpan(
                            style: TextStyle(color: Colors.black),
                            text: vo.itemcont),
                      ),
                      SizedBox(
                        height: 10,
                      ),
                      Container(
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(
                              Icons.star,
                              color: Colors.white,
                              size: 15,
                            ),
                            Text(
                              ' ${vo.sstar}',
                              style: TextStyle(color: Colors.white),
                            )
                          ],
                        ),
                        decoration: BoxDecoration(
                          color: Colors.black,
                          borderRadius: BorderRadius.circular(15.0),
                        ),
                        width: 50,
                      )
                    ],
                  ),
                  // 두번 째 컬럼 구분
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Expanded(
                        child: ClipRRect(
                          borderRadius: BorderRadius.circular(15.0),
                          child: Container(color: Colors.grey[300]),
                        ),
                      ),
                      Text('165,000',
                          style: TextStyle(
                            fontSize: 20,
                            fontWeight: FontWeight.w700,
                          )),
                      SizedBox(
                        height: 15,
                      ),
                      Text('쇼핑몰이름',
                          style: TextStyle(
                            fontSize: 16,
                            fontWeight: FontWeight.w700,
                          )),
                      Text('상품명',
                          style: TextStyle(
                            fontSize: 14,
                            fontWeight: FontWeight.w600,
                          )),
                      SizedBox(
                        height: 10,
                      ),
                      Container(
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.center,
                          children: [
                            Icon(
                              Icons.star,
                              color: Colors.white,
                              size: 15,
                            ),
                            Text(
                              ' 4.9',
                              style: TextStyle(color: Colors.white),
                            )
                          ],
                        ),
                        decoration: BoxDecoration(
                          color: Colors.black,
                          borderRadius: BorderRadius.circular(15.0),
                        ),
                        width: 50,
                      )
                    ],
                  ),
                ],
              ))
            ],
          ),
        );
      },
      childCount: (controller.shopResult.value.items!.length / 2).ceil(),
    ));
  }
}
