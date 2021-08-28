import 'package:get/get.dart';
import 'package:my_app/model/CommunityVO.dart';
import 'package:my_app/src/repository/community_repository.dart';

class BoarderinfoController extends GetxController {
  static BoarderinfoController get to => Get.find();

  Rx<CommunityVO> boardInfo = CommunityVO().obs;

  @override
  void onInit() {
    // TODO: implement onInit
    print(Get.parameters['bcd']);
    print(Get.parameters['mid']);

    dynamic param = {
      'bcd' :Get.parameters['bcd'],
      'mid' :Get.parameters['mid']
    };

    this._getBoardInfo(param);

    super.onInit();
  }

  void _getBoardInfo(dynamic param) async{
    CommunityVO result = await CommunityRepository.to.getBoardInfo(param);
    if(result != null){
    boardInfo(result);
    }
  }
}
