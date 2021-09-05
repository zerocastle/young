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

	// "보더리스트"
	public List<Map<String, Object>> getList(Map param) {

		return mapper.getList(param);
	}

	// "베스트 글 리스트"
	public List<Map<String, Object>> bestList() {
		return mapper.bestList();
	}

	// "게시글 상세 정보"
	public Map<String, Object> boardInfo(Map param) {
		return mapper.boardInfo(param);
	}

	// "댓글 리스트"
	public List<Map<String, Object>> repleInfo(Map param) {
		return mapper.repleInfo(param);
	};

	// "댓글 입력"
	public int repleInsert(Map param) {
		return mapper.repleInsert(param);
	}
}
