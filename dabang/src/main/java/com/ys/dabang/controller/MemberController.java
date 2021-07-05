package com.ys.dabang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ys.dabang.service.MemberService;
import com.ys.dabang.vo.MemberVo;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/member/**")
public class MemberController {

	private MemberService service;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<MemberVo> getList() throws Exception {

		return service.getList();

	}

}
