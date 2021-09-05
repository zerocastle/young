import 'package:get/get.dart';
import 'package:my_app/model/ShopResult.dart';
import 'package:my_app/src/repository/shop_repository.dart';
import 'package:flutter/cupertino.dart';

class ShoppingController extends GetxController {
  static ShoppingController get to => Get.find();

  ScrollController scrollController = ScrollController();

  Rx<ShopResult> shopResult = ShopResult(items: []).obs;

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
    print('사라짐??');
    super.dispose();
  }

  void _event() {
    scrollController.addListener(() {
      print(scrollController.position.maxScrollExtent);

      //스크롤
      var value = shopResult.value;
      var realEnd = value.realEnd ?? 0;
      var nextTokenTemp = value.nextToken ?? 0;
      if (scrollController.position.pixels ==
          scrollController.position.maxScrollExtent) {
        if (nextTokenTemp <= realEnd) {
          loading(true);
          _load();
        } else {
          //마지막 페이지
          hasMore(false);
          print('hasMore = > $hasMore');
        }
      }
    });
  }

  void _load() async {
    ShopResult result =
        await ShopRepository.to.loadData(shopResult.value.nextToken ?? 1);

    if (result != null && result.items != null && result.items!.length > 0) {
      shopResult.update((item) {
        var items2 = result.items;
        item!.nextToken = result.nextToken;
        item.realEnd = result.realEnd;
        item.items!.addAll(items2!);
        loading(false);
      });
    }
  }
}
