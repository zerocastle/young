import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/src/repository/community_repository.dart';

class CommunityController extends GetxController {
  static CommunityController get to => Get.find();

  ScrollController scrollController = ScrollController();

  Rx<CommunityResult> communityResult = CommunityResult(items: []).obs;

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

      //스크롤
      var value = communityResult.value;
      var realEnd = value.realEnd;
      if (scrollController.position.pixels ==
          scrollController.position.maxScrollExtent) {
            // if(communityResult.value.nextToken! <= realEnd!){
            _load();
            // }
      }
    });
  }

  void _load() async {
    CommunityResult result = await CommunityRepository.to
        .loadData(communityResult.value.nextToken ?? 1);

    if (result != null && result.items != null && result.items!.length > 0) {
      communityResult.update((item) {
        var items2 = result.items;
        item!.nextToken = result.nextToken;
        item.items!.addAll(items2!);
      });
    }
  }
}
