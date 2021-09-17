import 'package:carousel_slider/carousel_slider.dart';
import 'package:flutter/material.dart';
import 'package:flutter_rating_bar/flutter_rating_bar.dart';
import 'package:kakaomap_webview/kakaomap_webview.dart';

final List<String> imgList = [
  'https://images.unsplash.com/photo-1520342868574-5fa3804e551c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=6ff92caffcdd63681a35134a6770ed3b&auto=format&fit=crop&w=1951&q=80',
  'https://images.unsplash.com/photo-1522205408450-add114ad53fe?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=368f45b0888aeb0b7b08e3a1084d3ede&auto=format&fit=crop&w=1950&q=80',
  'https://images.unsplash.com/photo-1519125323398-675f0ddb6308?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=94a1e718d89ca60a6337a6008341ca50&auto=format&fit=crop&w=1950&q=80',
  'https://images.unsplash.com/photo-1523205771623-e0faa4d2813d?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=89719a0d55dd05e2deae4120227e6efc&auto=format&fit=crop&w=1953&q=80',
  'https://images.unsplash.com/photo-1508704019882-f9cf40e475b4?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=8c6e5e3aba713b17aa1fe71ab4f0ae5b&auto=format&fit=crop&w=1352&q=80',
  'https://images.unsplash.com/photo-1519985176271-adb1088fa94c?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=a0c8d632e977f94e5d312d9893258f59&auto=format&fit=crop&w=1355&q=80'
];

class DetailPage extends StatefulWidget {
  DetailPage({Key? key}) : super(key: key);

  @override
  State<DetailPage> createState() => _DetailPageState();
}

class _DetailPageState extends State<DetailPage> {
  final CarouselController _controller = CarouselController();
  final String _kakaoKey = '68ecc10f822c919e14b485a198bc5ecd';
  final String description =
      'People who are eligible for a COVID-19 vaccine but missed the chance to book a reservation will have another opportunity next month.\nThe Korea Disease Control and Prevention Agency(KDCA) said in a briefing on Thursday that vaccinations for some five million people over the age of 18 who haven’t received shots yet will run from October 1 to 16.\nReservations will open on September 18 from 8 p.m. until the 30th at 6 p.m. The Pfizer and Moderna vaccines will be offered.';

  int _current = 0;

  final TextStyle subTitleTextStyle =
  TextStyle(fontSize: 12, fontWeight: FontWeight.bold);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      body: SafeArea(
        child: CustomScrollView(
          physics: ClampingScrollPhysics(),
          slivers: [
            SliverAppBar(
              floating: true,
              backgroundColor: Colors.white,
              flexibleSpace: Stack(
                children: [
                  Container(
                      child: CarouselSlider(
                        options: CarouselOptions(
                            viewportFraction: 1.05,
                            height: 200,
                            aspectRatio: 2.0,
                            // enableInfiniteScroll: false,
                            enlargeCenterPage: false,
                            autoPlay: false,
                            onPageChanged: (index, reason) {
                              setState(() {
                                _current = index;
                              });
                            }),
                        items: imageSliders,
                      )),
                  Positioned(
                      left: 10,
                      top: 10,
                      child: Icon(
                        Icons.arrow_back,
                      )),
                  Positioned(
                      right: 10,
                      top: 10,
                      child: Icon(
                        Icons.menu,
                      )),
                  Positioned(
                      bottom: 10,
                      right: 15,
                      child: Row(
                        children: [
                          Container(
                              child: Icon(
                                Icons.share,
                                size: 20,
                              ),
                              padding: EdgeInsets.symmetric(
                                  vertical: 4, horizontal: 4),
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(6),
                              ),
                              alignment: Alignment.center),
                          const SizedBox(
                            width: 5,
                          ),
                          Container(
                              child: Icon(
                                Icons.favorite_border,
                                size: 20,
                              ),
                              padding: EdgeInsets.symmetric(
                                  vertical: 4, horizontal: 4),
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(6),
                              ),
                              alignment: Alignment.center),
                          const SizedBox(
                            width: 5,
                          ),
                          Container(
                              child: Icon(
                                Icons.thumb_up_alt_outlined,
                                size: 20,
                              ),
                              padding: EdgeInsets.symmetric(
                                  vertical: 4, horizontal: 4),
                              decoration: BoxDecoration(
                                color: Colors.white,
                                borderRadius: BorderRadius.circular(6),
                              ),
                              alignment: Alignment.center),
                        ],
                      )),
                  Positioned(
                    bottom: 5,
                    right: 0,
                    left: 0,
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: imgList.asMap().entries.map((entry) {
                        return GestureDetector(
                          onTap: () => _controller.animateToPage(entry.key),
                          child: Container(
                            width: 12.0,
                            height: 12.0,
                            margin: EdgeInsets.symmetric(
                                vertical: 8.0, horizontal: 4.0),
                            decoration: BoxDecoration(
                                shape: BoxShape.circle,
                                color: (Theme.of(context).brightness ==
                                    Brightness.dark
                                    ? Colors.white
                                    : Colors.black)
                                    .withOpacity(
                                    _current == entry.key ? 0.9 : 0.4)),
                          ),
                        );
                      }).toList(),
                    ),
                  )
                ],
              ),
              // Make the initial height of the SliverAppBar larger than normal.
              expandedHeight: 200,
            ),
            SliverList(
                delegate: SliverChildListDelegate([
                  const SizedBox(height: 20),
                  HorizontalPaddingWidget(
                    insideWidget: Text(
                      '업체명',
                      style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
                    ),
                  ),
                  const SizedBox(height: 8),
                  HorizontalPaddingWidget(
                    insideWidget: Row(
                      children: [
                        Text('대구광역시 수성구 범어',
                            style: TextStyle(
                                fontSize: 11, fontWeight: FontWeight.bold)),
                        const SizedBox(
                          width: 10,
                        ),
                        Row(
                          children: [
                            Text('지도보기',
                                style:
                                TextStyle(fontSize: 9, color: Colors.black45)),
                            Icon(
                              Icons.keyboard_arrow_right_outlined,
                              color: Colors.black45,
                              size: 14,
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 8),
                  HorizontalPaddingWidget(
                    insideWidget: Row(
                      children: [
                        Row(
                          children: [
                            Icon(
                              Icons.star,
                              size: 14,
                            ),
                            Text(
                              '2.5',
                              style: TextStyle(fontSize: 9),
                            ),
                          ],
                        ),
                        const SizedBox(
                          width: 10,
                        ),
                        Row(
                          children: [
                            Icon(
                              Icons.create,
                              size: 14,
                            ),
                            Text(
                              '524',
                              style: TextStyle(fontSize: 9),
                            ),
                          ],
                        ),
                        const Spacer(),
                        Row(
                          children: [
                            Icon(
                              Icons.phone,
                              size: 14,
                            ),
                            const SizedBox(
                              width: 4,
                            ),
                            Text(
                              '053-123-4567',
                              style: TextStyle(fontSize: 12),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 24),
                  HorizontalPaddingWidget(
                    insideWidget: Text(
                      '편의시설 및 서비스',
                      style: subTitleTextStyle,
                    ),
                  ),
                  const SizedBox(
                    height: 6,
                  ),
                  HorizontalPaddingWidget(
                    insideWidget: Wrap(
                      children: [
                        ConvenienceServiceWidget(
                          icon: Icon(Icons.car_rental),
                          title: '서비스 설명',
                        ),
                        ConvenienceServiceWidget(
                          icon: Icon(Icons.car_rental),
                          title: '서비스 설명',
                        ),
                        ConvenienceServiceWidget(
                          icon: Icon(Icons.car_rental),
                          title: '서비스 설명',
                        ),
                        ConvenienceServiceWidget(
                          icon: Icon(Icons.car_rental),
                          title: '서비스 설명',
                        ),
                        ConvenienceServiceWidget(
                          icon: Icon(Icons.car_rental),
                          title: '서비스 설명',
                        ),
                      ],
                    ),
                  ),
                  const SizedBox(height: 20),
                  HorizontalPaddingWidget(
                      insideWidget: Text(
                        '업체설명',
                        style: subTitleTextStyle,
                      )),
                  DescriptionTextWidget(description: description),
                  HorizontalPaddingWidget(
                      insideWidget: Text(
                        '객실정보',
                        style: subTitleTextStyle,
                      )),
                  const SizedBox(
                    height: 10,
                  ),
                  HorizontalPaddingWidget(
                    insideWidget: ListView.builder(
                      shrinkWrap: true,
                      physics: NeverScrollableScrollPhysics(),
                      itemBuilder: (context, index) {
                        return Padding(
                          padding: EdgeInsets.only(bottom: 20),
                          child: Row(
                            children: [
                              ClipRRect(
                                child: SizedBox(
                                  width: 100,
                                  height: 100,
                                  child: Image.network(
                                      'https://picsum.photos/id/237/100/100'),
                                ),
                                borderRadius: BorderRadius.circular(20),
                              ),
                              const SizedBox(
                                width: 10,
                              ),
                              Expanded(
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Text(
                                      '라벤더',
                                      style: TextStyle(
                                          fontSize: 12,
                                          fontWeight: FontWeight.bold),
                                    ),
                                    const SizedBox(height: 10),
                                    Row(
                                      children: [
                                        Text(
                                          '기준인원5',
                                          style: TextStyle(
                                              fontSize: 10,
                                              color: Colors.black45,
                                              fontWeight: FontWeight.bold),
                                        ),
                                        SizedBox(width: 10),
                                        Text(
                                          '최대인원8',
                                          style: TextStyle(
                                            fontSize: 10,
                                            color: Colors.black45,
                                            fontWeight: FontWeight.bold,
                                          ),
                                        )
                                      ],
                                    ),
                                    Align(
                                      alignment: Alignment.centerRight,
                                      child: Text('100,000원',
                                          style: TextStyle(
                                              decoration:
                                              TextDecoration.lineThrough,
                                              fontSize: 10,
                                              color: Colors.black45,
                                              fontWeight: FontWeight.bold)),
                                    ),
                                    SizedBox(
                                      height: 16,
                                    ),
                                    Align(
                                      alignment: Alignment.centerRight,
                                      child: Row(
                                        mainAxisAlignment: MainAxisAlignment.end,
                                        children: [
                                          Text(
                                            '20%',
                                            style: TextStyle(
                                                fontSize: 12,
                                                color: Colors.black45,
                                                fontWeight: FontWeight.bold),
                                          ),
                                          const SizedBox(
                                            width: 8,
                                          ),
                                          Text(
                                            '100,000원',
                                            style: TextStyle(
                                                fontSize: 14,
                                                color: Colors.black,
                                                fontWeight: FontWeight.bold),
                                          ),
                                        ],
                                      ),
                                    )
                                  ],
                                ),
                              )
                            ],
                          ),
                        );
                      },
                      itemCount: 2,
                    ),
                  ),
                  HorizontalPaddingWidget(
                      insideWidget: Text('오시는 길', style: subTitleTextStyle)),
                  const SizedBox(width: 16),
                  //카카오 맵 들어갈곳
                  HorizontalPaddingWidget(insideWidget: KakaoMapView(
                    width: double.infinity,
                    height: 150,
                    kakaoMapKey: _kakaoKey,
                    markerImageURL: 'https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/marker_red.png',
                    showMapTypeControl: false,
                    lat: 33.450701,
                    lng: 126.570667,
                  )),
                  const SizedBox(width: 16),
                  HorizontalPaddingWidget(
                    insideWidget: Text(
                      '대구광역시 수성구 범어',
                      style: TextStyle(
                          fontSize: 11,
                          color: Colors.black54,
                          fontWeight: FontWeight.bold),
                    ),
                  ),
                  const SizedBox(height: 40),
                  //리뷰
                  HorizontalPaddingWidget(
                    insideWidget: Row(
                      children: [
                        Text.rich(TextSpan(
                            text: '리뷰',
                            style: subTitleTextStyle,
                            children: [TextSpan(text: '(524)')])),
                        const Spacer(),
                        Icon(
                          Icons.star,
                          size: 12,
                        ),
                        Text(' 2.5')
                      ],
                    ),
                  ),
                  const SizedBox(height: 20),
                  HorizontalPaddingWidget(
                    insideWidget: Column(
                      children: [
                        Row(children: [
                          Container(
                            decoration: BoxDecoration(
                              color: Colors.black,
                              borderRadius: BorderRadius.circular(24),
                            ),
                            width: 60,
                            height: 60,
                            child: Center(
                              child: Icon(
                                Icons.person,
                                color: Colors.white,
                                size: 24,
                              ),
                            ),
                          ),
                          const SizedBox(width: 16),
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.start,
                            children: [
                              Text('냠냠'),
                              Row(
                                children: [
                                  RatingBar.builder(
                                      initialRating: 3,
                                      direction: Axis.horizontal,
                                      allowHalfRating: false,
                                      itemCount: 5,
                                      itemSize: 16,
                                      // itemPadding:
                                      //     EdgeInsets.symmetric(horizontal: 1),
                                      ignoreGestures: true,
                                      itemBuilder: (context, _) =>
                                          Icon(Icons.star, color: Colors.black),
                                      onRatingUpdate: (rating) {}),
                                  const SizedBox(width: 20),
                                  Text(
                                    '2021.06.23',
                                    style: TextStyle(
                                        fontSize: 10,
                                        color: Colors.black54,
                                        fontWeight: FontWeight.bold),
                                  )
                                ],
                              ),
                            ],
                          )
                        ]),
                        Row(
                          children: [
                            const SizedBox(width: 76),
                            Expanded(
                              child: Text(
                                  '주인이 친절한데 근처에 아무것도 없어요... 그리구 숙소가 너무 구석에 있어서 찾기 힘들었어요 ㅋㅋㅋ 그래도 잘 놀고 잘 쉬다 갑니다~~'),
                            ),
                          ],
                        ),
                      ],
                    ),
                  ),

                  const SizedBox(height: 16),
                  HorizontalPaddingWidget(
                      insideWidget: Container(
                          margin: EdgeInsets.symmetric(horizontal: 20),
                          padding: EdgeInsets.symmetric(vertical: 10),
                          decoration: BoxDecoration(
                            color: Colors.black,
                            borderRadius: BorderRadius.circular(4),
                          ),
                          child: Center(
                              child: Text('리뷰 더보기',
                                  style: TextStyle(
                                      color: Colors.white,
                                      fontWeight: FontWeight.bold))))),
                  const SizedBox(
                    height: 36,
                  ),
                  HorizontalPaddingWidget(
                      insideWidget: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            '환불정책',
                            style: subTitleTextStyle,
                          ),
                          const SizedBox(height: 20),
                          Text(
                            '[일반 환불 규정]',
                            style: TextStyle(fontSize: 10, fontWeight: FontWeight.bold),
                          ),
                          const SizedBox(height: 10),
                          Text(
                            '- 결제 후 24시간 이내일 경우 총 전액 환불 가능합니다.\n*단, 결제 24시간 이내 일지라도 체크인 29일 전인 경우 30% 수수료, 체크인 14일 전은 전액 환불 불가능합니다.\n\n- 결제24시간 이후 - 체크인 30일 전에 환불 요청 시, 총 금액의 10% 취소수수료가 발생합니다.',
                            style: TextStyle(
                              fontSize: 10,
                            ),
                          ),
                        ],
                      )),
                  const SizedBox(height: 40),
                  Container(
                    height: 50,
                    decoration: BoxDecoration(color: Colors.white, boxShadow: [
                      BoxShadow(
                        color: Colors.grey.withOpacity(0.5),
                        spreadRadius: 5,
                        blurRadius: 7,
                        offset: Offset(0, 5),
                      )
                    ]),
                    child: Row(
                      children: [
                        Padding(
                          padding: const EdgeInsets.symmetric(
                              horizontal: 20, vertical: 5),
                          child: Icon(Icons.insert_link),
                        ),
                        Expanded(
                          child: Container(
                            margin:
                            EdgeInsets.symmetric(horizontal: 16, vertical: 4),
                            decoration: BoxDecoration(
                                color: Colors.black,
                                borderRadius: BorderRadius.circular(8)),
                            child: Center(
                              child: Text(
                                '결제하기',
                                style: TextStyle(
                                    fontSize: 12,
                                    color: Colors.white,
                                    fontWeight: FontWeight.bold),
                              ),
                            ),
                          ),
                        )
                      ],
                    ),
                  )
                ]))
          ],
        ),
      ),
    );
  }

  final List<Widget> imageSliders = imgList
      .map((item) => Container(
    child: Container(
      margin: EdgeInsets.all(5.0),
      child: Image.network(item, fit: BoxFit.cover, width: 1000.0),
    ),
  ))
      .toList();
}

class ConvenienceServiceWidget extends StatelessWidget {
  const ConvenienceServiceWidget({
    Key? key,
    required this.icon,
    required this.title,
  }) : super(key: key);

  final Icon icon;
  final String title;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(vertical: 6, horizontal: 16),
      child: Column(
        children: [
          icon,
          const SizedBox(height: 6),
          Text(
            '$title',
            style: TextStyle(fontSize: 11),
          ),
        ],
      ),
    );
  }
}

class HorizontalPaddingWidget extends StatelessWidget {
  const HorizontalPaddingWidget({
    Key? key,
    required this.insideWidget,
  }) : super(key: key);
  final Widget insideWidget;

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16),
      child: insideWidget,
    );
  }
}

class DescriptionTextWidget extends StatefulWidget {
  const DescriptionTextWidget({
    Key? key,
    required this.description,
  }) : super(key: key);
  final String description;

  @override
  State<DescriptionTextWidget> createState() => _DescriptionTextWidgetState();
}

class _DescriptionTextWidgetState extends State<DescriptionTextWidget> {
  late String shortcut;
  late String more;
  final int cutLength = 150;

  bool flag = true;

  @override
  void initState() {
    super.initState();
    if (widget.description.length > cutLength) {
      shortcut = widget.description.substring(0, cutLength);
      more = widget.description.substring(cutLength, widget.description.length);
    } else {
      shortcut = widget.description;
      more = "";
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: new EdgeInsets.symmetric(horizontal: 10.0, vertical: 10.0),
      child: more.isEmpty
          ? new Text(shortcut)
          : new Column(
        children: <Widget>[
          new Text(flag ? (shortcut + "...") : (shortcut + more)),
          new InkWell(
            child: new Row(
              mainAxisAlignment: MainAxisAlignment.start,
              children: <Widget>[
                new Text(
                  flag ? "\n 더보기..." : "\n 가리기",
                  style: new TextStyle(color: Colors.black, fontSize: 12),
                ),
              ],
            ),
            onTap: () {
              setState(() {
                flag = !flag;
              });
            },
          ),
        ],
      ),
    );
  }
}
