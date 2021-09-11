class ShopVO {
  int? rnum;
  String? itemurl;
  int? totalCount;
  String? price;
  int? itemcd;
  String? itemcont;
  double? sstar;
  String? subtitle;
  String? indate;
  int? catecd;

  ShopVO({
    this.rnum,
    this.itemurl,
    this.totalCount,
    this.price,
    this.itemcd,
    this.itemcont,
    this.sstar,
    this.subtitle,
    this.indate,
    this.catecd,
  });

  factory ShopVO.fromJson(Map<String, dynamic> json) => ShopVO(
        rnum: json["RNUM"],
        itemurl: json["ITEMURL"],
        totalCount: json["TOTAL_COUNT"],
        price: json["PRICE"],
        itemcd: json["ITEMCD"],
        itemcont: json["ITEMCONT"],
        sstar: json["SSTAR"].toDouble(),
        subtitle: json["SUBTITLE"],
        indate: json["INDATE"],
        catecd: json["CATECD"],
      );

  Map<String, dynamic> toJson() => {
        "RNUM": rnum,
        "ITEMURL": itemurl,
        "TOTAL_COUNT": totalCount,
        "PRICE": price,
        "ITEMCD": itemcd,
        "ITEMCONT": itemcont,
        "SSTAR": sstar,
        "SUBTITLE": subtitle,
        "INDATE": indate,
        "CATECD": catecd,
      };
}
