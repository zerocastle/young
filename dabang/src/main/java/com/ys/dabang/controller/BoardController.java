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

	// �Խñ� �Ѹ���
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/getList")
	public List<Map<String, Object>> getList() throws Exception {
		return service.getList();

	}

	// ����Ʈ �� ����
	@CrossOrigin(origins = "*", maxAge = 3600)
	@GetMapping(value = "/bestList")
	public List<Map<String,Object>> bestList() throws Exception {
		return service.bestList();

	}
	
	// �Խñ� �� ���� ����
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/boardInfo")
	public List<Map<String, Object>> boardInfo(@RequestBody Map param) throws Exception {
		System.out.println("�Ѿ�� ������ : " + param);

		List<Map<String, Object>> resultData = service.boardInfo(param);

		System.out.println("��� ������ = >" + resultData);

		return resultData;
	}
	
	// ��� ����
	@CrossOrigin(origins = "*", maxAge = 3600)
	@PostMapping(value = "/repleInfo")
	public List<Map<String, Object>> repleInfo(@RequestBody Map param) throws Exception {
		System.out.println("�Ѿ�� ������ : " + param);

		List<Map<String, Object>> resultData = service.repleInfo(param);

		System.out.println("��� ������ = >" + resultData);

		return resultData;
	}

}
