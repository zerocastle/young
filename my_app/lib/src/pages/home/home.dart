// import 'package:my_app/src/app.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:my_app/data/network.dart';
import 'package:http/http.dart' as http;
import 'package:charts_flutter/flutter.dart' as charts;

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
    print('fuck');
    // String url = "http://localhost:3000/login";
    // String url = "http://192.168.15.4:3000/login";
    String url = "http://192.168.15.4:8181/member/getList";
    Network network = await Network(url);
    var data = await network.getJsonData();
    print(data);
    print(data[0]['email']);

    // List<MemberClass> list = [];
    // var size = data.length;
    // var testData = data[0][0];
    // print('size$size data$testData');
    // for(var i = 0; i < size; i++){
    //   MemberClass member = new MemberClass(mId: data[0][0],mPw: data[0][1]);
    //   list.add(member);
    // }

    // var toJson = member.toJson();
    // print(toJson);
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      body: Column(
        children: <Widget>[
          Center(
            child: Column(
              children: <Widget>[
                Container(
                margin: EdgeInsets.symmetric(vertical: 5),
                width: 300,
                height: 20,
                child: ClipRRect(
                borderRadius: BorderRadius.all(Radius.circular(10)),
                child: LinearProgressIndicator(
                  value: 0.1,
                  backgroundColor: Colors.grey,
                    ),
                  )
                ),
              
              ],
            ),
          )
        ],
      ),
    );
  }
}
