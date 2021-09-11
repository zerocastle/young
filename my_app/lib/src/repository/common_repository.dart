import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';

class CommonRepository extends GetConnect {
  static CommonRepository get to => Get.find();
  late User commmonUser;
  Rx<String> tempId = "".obs;
  @override
  void onInit() {
    // TODO: implement onInit
    super.onInit();
  }

  void setCommonUser(param){
    // this.commmonUser.update((val) { 
    //   val = param;
    // });
    commmonUser = param;
    print("==========================================================");
    print(this.commmonUser);
    tempId(this.commmonUser.email);
    print("==========================================================");
  }
}