class MemberClass {
  late String mId;
  late String mPw;
  late String email;
  late String mLocate;
  late String inDate;
  late String endDate;
  late String mType;
  late String intro;
  late double lat;
  late double lng;

  late String classCd;
  late String bTypeCd;
  late String aDeptCd;

  MemberClass(
      {String? mId = "",
      String? mPw = "",
      String? email = "",
      String? mLocate = "",
      String? inDate = "",
      String? endDate = "",
      String? mType = "",
      String? intro = "",
      double? lat = 0.0,
      double? lng = 0.0,
      String? classCd = "",
      String? bTypeCd = "",
      String? aDeptCd = ""}) {
  }

  MemberClass.fromJson(Map<String, dynamic> json) {
    mId = json['mId'];
    mPw = json['mPw'];
    email = json['email'];
    mLocate = json['mLocate'];
    inDate = json['inDate'];
    endDate = json['endDate'];
    mType = json['mType'];
    intro = json['intro'];
    lat = json['lat'];
    lng = json['lng'];
    classCd = json['classCd'];
    bTypeCd = json['bTypecd'];
    aDeptCd = json['aDeptCd'];
  }

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = new Map<String, dynamic>();
    data['mId'] = this.mId;
    data['mPw'] = this.mPw;
    data['email'] = this.email;
    data['mLocate'] = this.mLocate;
    data['inDate'] = this.inDate;
    data['endDate'] = this.endDate;
    data['mType'] = this.mType;
    data['intro'] = this.intro;
    data['lat'] = this.lat;
    data['lng'] = this.lng;
    data['classCd'] = this.classCd;
    data['bTypeCd'] = this.bTypeCd;
    data['aDeptCd'] = this.aDeptCd;
    return data;
  }
}
