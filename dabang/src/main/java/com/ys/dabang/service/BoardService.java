package com.ys.dabang.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ys.dabang.mapper.BoardMapper;
import com.ys.dabang.vo.BoardVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private BoardMapper mapper;

	// 게시글 불러오기
	public List<Map<String, Object>> getList() {

		return mapper.getList();
	}

	// 베스트 게시글 불러오기
	public List<Map<String, Object>> bestList() {
		return mapper.bestList();
	}

	// 글 상세 정보 들고오기
	public List<Map<String, Object>> boardInfo(Map param) {
		return mapper.boardInfo(param);
	}

	// 댓글 가지고 오기
	public List<Map<String, Object>> repleInfo(Map param) {
		return mapper.repleInfo(param);
	};

	// 댓글 입력
	public int repleInsert(Map param) {
		return mapper.repleInsert(param);
	}
}
