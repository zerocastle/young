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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ys.dabang.service.BoardService;

import lombok.AllArgsConstructor;
import medios.cmmn.PageInfoDTO;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/board/**")
public class BoardController {

	private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	// 보더 리스트 페이징 처리
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public Map<String, Object> getList(
			@RequestParam(value = "nextToken", required = false, defaultValue = "1") int nextToken) throws Exception {
		PageInfoDTO pageInfoDTO = new PageInfoDTO(nextToken);
		Map<String, Object> param = pageInfoDTO.makePageNumber();

		param.put("START", param.get("start"));
		param.put("END", param.get("end"));
		List<Map<String, Object>> temp = service.getList(param);

		List itemList = new ArrayList<>();
		Map<String, Object> pageInfo = new HashMap<String, Object>();

		Map<String, Object> resultMap = new HashMap<String, Object>();

		for (int i = 0; i < temp.size(); i++) {
			itemList.add(temp.get(i));
		}

		int realEnd = (int) (Math.ceil(Integer.parseInt(temp.get(0).get("TOTAL_COUNT").toString()) / 10.0));
		System.out.println("realEnd = > " + realEnd);

		pageInfo.put("TOTAL_COUNT", temp.get(0).get("TOTAL_COUNT"));
		pageInfo.put("NTEXT_TOKEN", param.get("nextToken"));
		pageInfo.put("PER_PAGE", param.get("perPage"));
		pageInfo.put("REAL_END", realEnd);

		resultMap.put("pageInfo", pageInfo);
		resultMap.put("items", itemList);

		System.out.println("보더 리스트들 = > " + resultMap);

		return resultMap;

	}

	// 베스트글 리스트
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/bestList")
	public List<Map<String, Object>> bestList() throws Exception {
		return service.bestList();

	}

	// 게시글 정보
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/boardInfo")
	public Map<String, Object> boardInfo(@RequestBody Map param) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("boardInfo", service.boardInfo(param));
		return service.boardInfo(param);
	}

	// 댓글 리스트
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/repleInfo")
	public List<Map<String, Object>> repleInfo(@RequestBody Map param) throws Exception {
		List<Map<String, Object>> resultData = service.repleInfo(param);
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
