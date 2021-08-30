import 'package:firebase_auth/firebase_auth.dart';
import 'package:get/get.dart';

class commonRepository extends GetConnect {
  static commonRepository get to => Get.find();
  late User commmonUser;
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
  }
}