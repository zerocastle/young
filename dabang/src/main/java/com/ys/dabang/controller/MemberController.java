package com.ys.dabang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.dabang.service.MemberService;
import com.ys.dabang.vo.MemberVo;

import lombok.AllArgsConstructor;
import medios.cmmn.collection.MData;
import medios.cmmn.collection.MMultiData;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/member/**")
public class MemberController {

	private MemberService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<MemberVo> getList() throws Exception {
		
		MData param = new MData();
		MMultiData result = new MMultiData();
		/*
		 * param.set("test1", "test1"); param.set("test2","test2");
		 * 
		 * result.addMData("result", param); System.out.println(result);
		 */
		

		return service.getList();
		
//		return param;

	}

}
