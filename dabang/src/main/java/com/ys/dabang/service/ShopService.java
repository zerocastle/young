package com.ys.dabang.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ys.dabang.mapper.ShopMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ShopService {

	private ShopMapper mapper;

	// 게시글 들고오기
	public List<Map<String, Object>> getList(Map param) {
		return mapper.getList(param);
	}

}
