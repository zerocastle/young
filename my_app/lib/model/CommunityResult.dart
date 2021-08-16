import 'package:my_app/model/CommunityVO.dart';

class CommunityResult {
  int? totalCount;
  int? nextToken;
  int? perPage;
  List<CommunityVO>? items;

  CommunityResult({
    this.totalCount,
    this.nextToken,
    this.perPage,
    this.items,
  });

  factory CommunityResult.fromJson(Map<String, dynamic> json) =>
      CommunityResult(
          totalCount: json["pageInfo"]["TOTAL_COUNT"],
          nextToken: json["pageInfo"]["NTEXT_TOKEN"],
          perPage: json["pageInfo"]["PER_PAGE"],
          items: List<CommunityVO>.from(
              json["items"].map((data) => CommunityVO.fromJson(data))));
}
