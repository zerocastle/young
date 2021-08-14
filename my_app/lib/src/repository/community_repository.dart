import 'package:get/get.dart';

class CommunityRepository extends GetConnect{
  static CommunityRepository get to => Get.find();

  @override
  void onInit() {
    // TODO: implement onInit
    httpClient.baseUrl = "http://192.168.15.4:8181";
    super.onInit();
  }

  loadData() async{
    String url = "/board/getList";
    final response = await get(url);
    if(response.status.hasError){
      return Future.error(response.statusText.toString());
    }else{
      print('==================== 좃댔다 ==============');
      print(response.body);
    }
  }
}