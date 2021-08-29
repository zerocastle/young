import 'package:get/get.dart';
import 'package:my_app/model/CommunityVO.dart';
import 'package:my_app/model/repleVo.dart';
import 'package:my_app/src/repository/community_repository.dart';

class BoarderinfoController extends GetxController {
  static BoarderinfoController get to => Get.find();

  // 보더 인포
  Rx<CommunityVO> boardInfo = CommunityVO().obs;
  
  // 댓글정보
  Rx<RepleVo> repleVoList = RepleVo(items: []).obs;

  @override
  void onInit() {
    // TODO: implement onInit
    print(Get.parameters['bcd']);
    print(Get.parameters['mid']);

    dynamic param = {
      'bcd' :Get.parameters['bcd'],
      'mid' :Get.parameters['mid']
    };

    this._getBoarderInfoBoss(param);

    super.onInit();
  }

  void _getBoarderInfoBoss(dynamic param) async{
    this._getBoardInfo(param).then((value) => _getRepleInfo(param));
  }

  Future<CommunityVO> _getBoardInfo(dynamic param) async{
    CommunityVO result = await CommunityRepository.to.getBoardInfo(param);
    if(result != null){
    boardInfo(result);
    }
    return result;
  }

  void _getRepleInfo(dynamic param) async{
    RepleVo result = await CommunityRepository.to.getRepleInfo(param);
    if(result != null && result.items!.length != null && result.items!.length > 0){
      repleVoList.update((item) {
        item!.items!.assignAll(result.items!);
       });
    }
  }

  
}
