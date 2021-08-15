import 'package:my_app/model/CommunityVO.dart';

class CommunityResult {
  int? totalCount;

  List<CommunityVO>? items;

  CommunityResult({this.totalCount, this.items});

  factory CommunityResult.fromJson(Map<String, dynamic> json) =>
      CommunityResult(
          totalCount: json["pageInfo"]["TOTAL_COUNT"],
          items: List<CommunityVO>.from(
              json["items"].map((data) => CommunityVO.fromJson(data))));
}
