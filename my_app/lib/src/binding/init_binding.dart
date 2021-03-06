import 'package:get/get.dart';
import 'package:my_app/src/controller/common_controller.dart';
import 'package:my_app/src/controller/community_controller.dart';
import 'package:my_app/src/controller/shopping_controller.dart';
import 'package:my_app/src/repository/common_repository.dart';
import 'package:my_app/src/repository/community_repository.dart';
import 'package:my_app/src/repository/shop_repository.dart';

class InitBinding implements Bindings {
  @override
  void dependencies() {
    // TODO: implement dependencies

    Get.put(CommunityRepository());
    //Get.put(CommunityController());
    // Get.put(CommonRepository());
    Get.put(CommonController());

    Get.put(ShopRepository());
  }
}
