package com.ys.dabang.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ys.dabang.mapper.VipBoardMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VipBoardService {

	private VipBoardMapper mapper;

	// �Խñ� �ҷ�����
	public List<Map<String,Object>> getList() {

		return mapper.getList();
	}
}
