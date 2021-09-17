import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/src/repository/community_repository.dart';

class EventController extends GetxController {
  static EventController get to => Get.find();

  // 로딩
  RxBool loading = false.obs;
  
  // 아이템이 더 있는지
  RxBool hasMore = true.obs;

  @override
  void onInit() {
    // TODO: implement onInit
  
    super.onInit();
  }


}
