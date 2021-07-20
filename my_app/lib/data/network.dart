import 'package:http/http.dart' as http;
import 'dart:convert';

class Network {
  final String url;
  Network(this.url);

  // 겟 데이터
  Future<dynamic> getJsonData() async {
    http.Response response = await http.get(Uri.parse(url));
    // print(response.body);
    if (response.statusCode == 200) {
      String jsonData = response.body;
      //print('jsonData $jsonData');
      var parsingData = jsonDecode(jsonData);

      return parsingData;
    }else{
      return null;
    }
  }

  // ======================== boarderInfo start 포스트 요청 ===========================
   Future<dynamic> executePost(dynamic data) async{
    final response = await http.post(Uri.parse(url),
    body: jsonEncode({
      'mid' : data['mid'].toString(),
      'bcd' : data['bcd'].toString()
    }),
    headers: {'Content-Type' : "application/json"},
    );

    if(response.statusCode == 200){
      String jsonData = response.body;
      var parsingData = jsonDecode(jsonData);
      return parsingData; 
    }else{
      return null;
    }
  }
}
