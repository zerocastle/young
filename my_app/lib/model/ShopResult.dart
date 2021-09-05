import 'package:my_app/model/ShopVO.dart';

class ShopResult {
  int? totalCount;
  int? nextToken;
  int? perPage;
  int? realEnd;
  List<ShopVO>? items;

  ShopResult({
    this.totalCount,
    this.nextToken,
    this.perPage,
    this.realEnd,
    this.items,
  });

  factory ShopResult.fromJson(Map<String, dynamic> json) => ShopResult(
      totalCount: json["pageInfo"]["TOTAL_COUNT"],
      nextToken: json["pageInfo"]["NTEXT_TOKEN"],
      perPage: json["pageInfo"]["PER_PAGE"],
      realEnd: json["pageInfo"]["REAL_END"],
      items: List<ShopVO>.from(
          json["items"].map((data) => ShopVO.fromJson(data))));
}
