import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/src/repository/community_repository.dart';

class EventInfoController extends GetxController {
  static EventInfoController get to => Get.find();

  ScrollController scrollController = ScrollController();

 @override
  void onInit() {
    // TODO: implement onInit
    print(Get.parameters['bcd']);
    print(Get.parameters['mid']);

    dynamic param = {
      'bcd' :Get.parameters['bcd'],
      'mid' :Get.parameters['mid']
    };

    super.onInit();
  }

}
