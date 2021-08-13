import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

class HomeController extends GetxController {
  static HomeController get to => Get.find();

  ScrollController scrollController = ScrollController();

  @override
  void onInit() {
    // TODO: implement onInit
    //_load();
    _event();
    super.onInit();
  }

  void _event() {
    scrollController.addListener(() {
      print(scrollController.position.maxScrollExtent);

      if (scrollController.position.pixels ==
          scrollController.position.maxScrollExtent) {
        print('reload');
      }
    });
  }
}
