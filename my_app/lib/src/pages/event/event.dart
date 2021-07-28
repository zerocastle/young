import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:my_app/data/network.dart';
import 'package:provider/provider.dart';
class Event extends StatefulWidget {
  final User user;
  const Event({Key? key, required this.user}) : super(key: key);

  @override
  _EventState createState() => _EventState();
}

class _EventState extends State<Event> {
  // 펜션
  // 게시글
  late Future<dynamic> _vipboards;


  final ScrollController _scrollController = ScrollController();
  @override
  void initState() {
    // TODO: implement initState
    super.initState();

    _vipboards = boardOrder('/vipboard/getList');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: Row(
          children: [
            cateBtn("숙박"),
            cateBtn("외식"),
            cateBtn("병원"),
            cateBtn("서비스"),
            ElevatedButton(
              onPressed: () {},
              child: Icon(Icons.search),
            ),
          ],
        ),
          // backgroundColor: Colors.green,
          centerTitle: true,
        ),
      body:SafeArea(
        child: CustomScrollView(
          slivers: <Widget>[
            SliverToBoxAdapter
            (child:      
              Padding(
                padding: const EdgeInsets.symmetric(horizontal: 10.0, vertical: 5.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.stretch,
                  children: [
                    Text("인기업체", style: TextStyle(fontWeight: FontWeight.w700, fontSize: 18),),
                    SizedBox(height: 35,),
                    SizedBox(
                      height: 500,
                      child: getShopList(_vipboards), 
                      ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }


}

FutureBuilder getShopList(Future<dynamic> _vipboards) {
  return FutureBuilder(
    future: _vipboards,
    builder: (BuildContext context, AsyncSnapshot snapshot) {
      print("context $context");
        print(snapshot.data.toString());
      if(!snapshot.hasData)
        return Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CircularProgressIndicator()
            ],
          ),
        );
      


      return ListView.builder(
      itemCount: snapshot.data.length,
      itemBuilder: (context, index) {
      // num number = index + 1;
      dynamic root = snapshot.data[index];
      // 타이틀
      String title = root['VTITLE'].toString();
      // 컨텐츠
      String content = root['VCONT'].toString();
      // 주소
      String locate = root['LOCATE'].toString();
      // 가격
      String price = root['PRICE'].toString();
      // 별점
      String vstar = root['VSTAR'].toString();
      // 이미지 (파일 이미지 나중에 추가)
      Image image =  Image(image: AssetImage('assets/room.png'), fit: BoxFit.fill, );
      // CircleAvatar image = CircleAvatar(backgroundImage: AssetImage('assets/fire.png'),);
      // 이름
      String name = root['MID'].toString();
      // 글 올린 날짜
      String date = root['VDATE'].toString();

         return Card(
           child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Container(
                    width: double.infinity,
                    height: 230,
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(15.0),
                      child: image,
                      ),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(15.0)
                    ),
                  ),
                  SizedBox(height: 20,),
                  Text('$title', style: TextStyle(fontSize: 20, fontWeight: FontWeight.w700),),
                  Row(
                    children: [
                      Icon(Icons.star),
                      Text("$vstar", style: TextStyle(fontSize: 12, color: Colors.grey[600]),),
                      SizedBox(width: 5,),
                      Text(" | ", style: TextStyle(fontSize: 12, color: Colors.grey[600]),),
                      SizedBox(width: 5,),
                      Text("$locate", style: TextStyle(fontSize: 12, fontWeight: FontWeight.w600),),
                    ],
                    ),
                  Row(
                    children: [
                      Text('$price원', style: TextStyle(fontSize: 20, fontWeight: FontWeight.w700),),
                      SizedBox(width: 10, ),
                      Text('최대 80% ', style: TextStyle(fontSize: 12, color: Colors.grey[600]),),
                    ],
                  ),
                ],
              )
         );
        }
      );
    },
  );
}


  Widget cateBtn(cate) {
    return SizedBox(
          child:  ElevatedButton(
            onPressed: () {},
            child: Text(cate, style: TextStyle(fontSize: 12),),
          ),
        );
  }

// 오더
Future<dynamic> boardOrder(String order) async {
  // String url = "http://localhost:3000/login";
  // String url = "http://192.168.15.4:3000/login";
  // String url = "http://192.168.15.4:8181" + order;
  String url = "http://192.168.219.106:8080" + order;
  print("url ::: $url");
  Network network =  Network(url);
  var data = await network.getJsonData();
  print("data :::$data");
  // Iterable l =data;
  // var listItem = (data as List).map((e) => Board.fromJson(e)).toList();
  // List<Board> listItem = List<Board>.from(l.map((e) => Board.fromJson(e)));
  return data;
}
