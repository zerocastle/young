import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:my_app/model/CommunityResult.dart';
import 'package:my_app/src/repository/community_repository.dart';

class CommunityController extends GetxController {
  static CommunityController get to => Get.find();

  ScrollController scrollController = ScrollController();

  Rx<CommunityResult> communityResult = CommunityResult(items: []).obs;

  // 로딩
  RxBool loading = false.obs;
  
  // 아이템이 더 있는지
  RxBool hasMore = true.obs;

  @override
  void onInit() {
    // TODO: implement onInit
    _load();
    _event();
    super.onInit();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    scrollController.dispose();
    print('사라짐??');
    super.dispose();
  }

  void _event() {
    scrollController.addListener(() {
      print(scrollController.position.maxScrollExtent);

      //스크롤
      var value = communityResult.value;
      var realEnd = value.realEnd ?? 0;
      var nextTokenTemp = value.nextToken ?? 0;
      if (scrollController.position.pixels ==
          scrollController.position.maxScrollExtent) {
        if (nextTokenTemp <= realEnd) {
          loading(true);
          _load();
        }else{
          //마지막 페이지
          hasMore(false);
          print('hasMore = > $hasMore');
        }
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
        item.realEnd = result.realEnd;
        item.items!.addAll(items2!);
        loading(false);
      });
    }
  }
}
