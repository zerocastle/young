import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';

class CommonController extends GetxController {
  static CommonController get to => Get.find();

  dynamic user;

  void changeUser(dynamic data) {
    this.user = data;
    update();
  }
}
