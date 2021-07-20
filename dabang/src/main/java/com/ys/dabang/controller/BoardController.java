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

import com.ys.dabang.service.BoardService;
import com.ys.dabang.vo.BoardVo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/board/**")
public class BoardController {

	private BoardService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<BoardVo> getList() throws Exception {
		return service.getList();

	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/bestList")
	public List<BoardVo> bestList() throws Exception {
		return service.bestList();

	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/boardInfo")
	public List<BoardVo> boardInfo(@RequestBody Map param) throws Exception {
		System.out.println("넘어온 데이터 : " + param);
		return service.boardInfo(param);
	}

	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/repleInfo")
	public List<BoardVo> repleInfo(@RequestBody Map param) throws Exception {
		System.out.println("넘어온 데이터 : " + param);
		return service.repleInfo(param);
	}

}
