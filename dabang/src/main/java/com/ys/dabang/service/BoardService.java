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

	// 테스트 맴버리스트 불러오기
	public List<BoardVo> getList() {

		return mapper.getList();
	}

	// 베스트 게시글 불러오기
	public List<BoardVo> bestList() {
		return mapper.bestList();
	}

	// 글 상세 정보 들고오기
	public List<BoardVo> boardInfo(Map param) {
		return mapper.boardInfo(param);
	}

	// 댓글 가지고 오기
	public List<BoardVo> repleInfo(Map param) {
		return mapper.repleInfo(param);
	};
}
