package com.ys.dabang.vo;

import lombok.Data;

@Data
public class BoardVo {
	private int bCd;

	private String bTitle;
	private String bCont;
	private String bDate;
	private int bLike;
	private int bVisit;
	private String mId;

}
