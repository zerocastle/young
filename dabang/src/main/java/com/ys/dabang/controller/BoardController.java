package com.ys.dabang.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.ys.dabang.service.BoardService;
import com.ys.dabang.vo.BoardVo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/board/**")
public class BoardController {

	private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 占쌉시깍옙 占싼몌옙占쏙옙
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<Map<String, Object>> getList() throws Exception {
		System.out.println("보더 리스트들 = > " + service.getList());
		return service.getList();

	}

	// 占쏙옙占쏙옙트 占쏙옙 占쏙옙占쏙옙
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/bestList")
	public List<Map<String, Object>> bestList() throws Exception {
		return service.bestList();

	}

	// 占쌉시깍옙 占쏙옙 占쏙옙占쏙옙 占쏙옙占쏙옙
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/boardInfo")
	public List<Map<String, Object>> boardInfo(@RequestBody Map param) throws Exception {
		System.out.println("占싼억옙占� 占쏙옙占쏙옙占쏙옙 : " + param);

		List<Map<String, Object>> resultData = service.boardInfo(param);

		System.out.println("占쏙옙占� 占쏙옙占쏙옙占쏙옙 = >" + resultData);

		return resultData;
	}

	// 占쏙옙占� 占쏙옙占쏙옙
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/repleInfo")
	public List<Map<String, Object>> repleInfo(@RequestBody Map param) throws Exception {
		System.out.println("占싼억옙占� 占쏙옙占쏙옙占쏙옙 : " + param);

		List<Map<String, Object>> resultData = service.repleInfo(param);

		System.out.println("占쏙옙占� 占쏙옙占쏙옙占쏙옙 = >" + resultData);

		return resultData;
	}

	// 댓글 입력
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/repleInsert")
	public Map repleInsert(@RequestBody Map param) throws Exception {

		int count = service.repleInsert(param);

		System.out.println("count = > " + count);

		Map<String, String> result = new HashMap<String, String>();

		result.put("result", (count > 0) ? "success" : "fail");

		return result;
	}

}
