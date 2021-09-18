import 'package:get/get.dart';
import 'package:my_app/model/ShopResult.dart';
import 'package:my_app/model/ShopVO.dart';

class ShopRepository extends GetConnect {
  static ShopRepository get to => Get.find();

  @override
  void onInit() {
    // TODO: implement onInit
    //httpClient.baseUrl = "http://192.168.15.4:8181";
    httpClient.baseUrl = "http://192.168.219.101:8181";
    super.onInit();
  }

  Future<ShopResult> loadData(int nextToken) async {
    print('nextToken$nextToken');
    String url = "/shop/getList?nextToken=${nextToken.toString()}";
    final response = await get(url);
    if (response.status.hasError) {
      return Future.error(response.statusText.toString());
    } else {
      print('==================== okok ==============');
      print(response.body);
      if (response.body["items"] != null && response.body["items"].length > 0) {
        return ShopResult.fromJson(response.body);
      } else {
        return Future.error(response.statusText.toString());
      }
    }
  }
}
