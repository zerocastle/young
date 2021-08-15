import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/src/repository/community_repository.dart';

class CommunityController extends GetxController {
  static CommunityController get to => Get.find();

  ScrollController scrollController = ScrollController();

  Rx<CommunityResult> communityResult = CommunityResult().obs;

  @override
  void onInit() {
    // TODO: implement onInit
    _load();
    _event();
    super.onInit();
  }

  void _event() {
    scrollController.addListener(() {
      print(scrollController.position.maxScrollExtent);

      if (scrollController.position.pixels ==
          scrollController.position.maxScrollExtent) {
        print('reload');
        _load();
      }
    });
  }

  void _load() async{
    CommunityResult result = await CommunityRepository.to.loadData();
    
    if(result != null && result.items != null && result.items!.length > 0){
      communityResult(result);
    }

  }
}
