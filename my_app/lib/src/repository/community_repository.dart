import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';

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
}
