import 'dart:convert';

import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/model/CommunityVO.dart';

class CommunityRepository extends GetConnect {
  static CommunityRepository get to => Get.find();

  @override
  void onInit() {
    // TODO: implement onInit
    httpClient.baseUrl = "http://192.168.15.4:8181";
    super.onInit();
  }

  Future<CommunityResult> loadData(int nextToken) async {
    print('nextToken$nextToken');
    String url = "/board/getList?nextToken=${nextToken.toString()}";
    final response = await get(url);
    if (response.status.hasError) {
      return Future.error(response.statusText.toString());
    } else {
      print('==================== okok ==============');
      print(response.body);
      if (response.body["items"] != null && response.body["items"].length > 0) {
        return CommunityResult.fromJson(response.body);
      } else {
        return Future.error(response.statusText.toString());
      }
    }
  }


  // 보더 정보 들고오기
  Future<CommunityVO> getBoardInfo(dynamic data) async {
    var body = jsonEncode(
        {'bcd': data['bcd'].toString(), 'mid': data['mid'].toString()});
    final response = await post("/board/boardInfo", body);

    if (response.statusCode == 200) {
      print(response.body);
      return CommunityVO.fromJson(response.body);
    } else {
      print('shit');
      return CommunityVO();
    }
  }
}
