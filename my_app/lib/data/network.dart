import 'package:http/http.dart' as http;
import 'dart:convert';

class Network {
  final String url;
  Network(this.url);

  Future<dynamic> getJsonData() async {
    http.Response response = await http.get(Uri.parse(url));
    // print(response.body);
    if (response.statusCode == 200) {
      String jsonData = response.body;
      print('jsonData $jsonData');
      var parsingData = jsonDecode(jsonData);

      return parsingData;
    }
  }
}