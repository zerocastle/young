package com.ys.dabang.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.dabang.service.VipBoardService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/vipboard/**")
public class VipBoardController {

	private VipBoardService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 이벤트 리스트
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<Map<String, Object>> getList() throws Exception {
		return service.getList();

	}
	

}
