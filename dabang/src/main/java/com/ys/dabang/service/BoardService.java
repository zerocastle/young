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

	// �Խñ� �ҷ�����
	public List<Map<String, Object>> getList(Map param) {

		return mapper.getList(param);
	}

	// ����Ʈ �Խñ� �ҷ�����
	public List<Map<String, Object>> bestList() {
		return mapper.bestList();
	}

	// �� �� ���� ������
	public List<Map<String, Object>> boardInfo(Map param) {
		return mapper.boardInfo(param);
	}

	// ��� ������ ����
	public List<Map<String, Object>> repleInfo(Map param) {
		return mapper.repleInfo(param);
	};

	// ��� �Է�
	public int repleInsert(Map param) {
		return mapper.repleInsert(param);
	}
}
