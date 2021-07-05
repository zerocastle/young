package com.ys.dabang.vo;

import lombok.Data;

@Data
public class MemberVo {

	private String mId;
	private String mPw;
	private String email;
	private String mLocate;
	private String inDate;
	private String endDate;
	private String mType;
	private String intro;
	private String lat;
	private String lng;

	private String classCd;
	private String bTypeCd;
	private String aDeptCd;

}
