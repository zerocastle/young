class RepleVo {
  int? cdate;
  int? bcd;
  String? mpoint;
  int? ccd;
  String? classcd;
  String? mid;
  String? ccont;
  String? classnm;

  List<RepleVo>? items;

  RepleVo({
    this.cdate,
    this.bcd,
    this.mpoint,
    this.ccd,
    this.classcd,
    this.mid,
    this.ccont,
    this.classnm,

    this.items
  });

  factory RepleVo.fromJson(Map<String, dynamic> json) => RepleVo(
        cdate: json["CDATE"],
        bcd: json["BCD"],
        mpoint: json["MPOINT"],
        ccd: json["CCD"],
        classcd: json["CLASSCD"],
        mid: json["MID"],
        ccont: json["CCONT"],
        classnm: json["CLASSNM"]
      );

  Map<String, dynamic> toJson() => {
        "CDATE": cdate,
        "BCD": bcd,
        "MPOINT": mpoint,
        "CCD": ccd,
        "CLASSCD": classcd,
        "MID": mid,
        "CCONT": ccont,
        "CLASSNM": classnm,
      };

    factory RepleVo.toList(Map<String,dynamic> json) => 
    RepleVo(
     items: List<RepleVo>.from(json["items"].map((data)=>RepleVo.fromJson(data)))
    );
}
