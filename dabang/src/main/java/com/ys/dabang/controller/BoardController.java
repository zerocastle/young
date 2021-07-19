package com.ys.dabang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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

}
