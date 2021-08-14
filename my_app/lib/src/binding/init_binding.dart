import 'package:get/get.dart';
import 'package:my_app/src/repository/community_repository.dart';

class InitBinding implements Bindings{
  @override
  void dependencies() {
    // TODO: implement dependencies

    Get.put(CommunityRepository(),permanent: true);
    
  }
}