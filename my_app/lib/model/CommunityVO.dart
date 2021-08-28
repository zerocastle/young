class CommunityVO {
  String /*!*/ ? bdate;
  int /*!*/ ? rec;
  int /*!*/ ? bcd;
  String? btitle;
  String? bcont;
  int /*!*/ ? bvisit;
  String /*!*/ ? mt;
  String? mid;
  int? blike;

  String? classnm;
  String? mpoint;

  CommunityVO({
    this.bdate,
    this.rec,
    this.bcd,
    this.btitle,
    this.bcont,
    this.bvisit,
    this.mt,
    this.mid,
    this.blike,

    this.classnm,
    this.mpoint
  });

  factory CommunityVO.fromJson(Map<String, dynamic> json) => CommunityVO(
        bdate: json["BDATE"],
        rec: json["REC"],
        bcd: json["BCD"],
        btitle: json["BTITLE"],
        bcont: json["BCONT"],
        bvisit: json["BVISIT"],
        mt: json["MT"],
        mid: json["MID"],
        blike: json["BLIKE"],

        classnm: json["CLASSNM"],
        mpoint: json["MPOINT"]
      );

  Map<String, dynamic> toJson() => {
        "BDATE": bdate,
        "REC": rec,
        "BCD": bcd,
        "BTITLE": btitle,
        "BCONT": bcont,
        "BVISIT": bvisit,
        "MT": mt,
        "MID": mid,
        "BLIKE": blike,

        "CLASSNM" : classnm,
        "MPOINT" : mpoint
      };
}
