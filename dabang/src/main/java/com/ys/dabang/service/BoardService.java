package com.ys.dabang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ys.dabang.mapper.BoardMapper;
import com.ys.dabang.vo.BoardVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BoardService {

	private BoardMapper mapper;

	// �׽�Ʈ �ɹ�����Ʈ �ҷ�����
	public List<BoardVo> getList() {

		return mapper.getList();
	}

	// ����Ʈ �Խñ� �ҷ�����
	public List<BoardVo> bestList() {
		return mapper.bestList();
	}

}