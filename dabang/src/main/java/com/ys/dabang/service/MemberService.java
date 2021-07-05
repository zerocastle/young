package com.ys.dabang.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ys.dabang.mapper.MemberMapper;
import com.ys.dabang.vo.MemberVo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberService {

	private MemberMapper mapper;

	// �׽�Ʈ �ɹ�����Ʈ �ҷ�����
	public List<MemberVo> getList() {

		return mapper.getList();
	}

}
