import 'package:my_app/model/CommunityVO.dart';

class CommunityResult {
  int? totalCount;
  int? nextToken;
  int? perPage;
  int? realEnd;
  List<CommunityVO>? items;

  CommunityResult({
    this.totalCount,
    this.nextToken,
    this.perPage,
    this.realEnd,
    this.items,
  });

  factory CommunityResult.fromJson(Map<String, dynamic> json) =>
      CommunityResult(
          totalCount: json["pageInfo"]["TOTAL_COUNT"],
          nextToken: json["pageInfo"]["NTEXT_TOKEN"],
          perPage: json["pageInfo"]["PER_PAGE"],
          realEnd: json["pageInfo"]["REAL_END"],
          items: List<CommunityVO>.from(
              json["items"].map((data) => CommunityVO.fromJson(data))));
}
