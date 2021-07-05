/**
 * 
 */
package com.ys.dabang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ys.dabang.vo.MemberVo;

/**
 * @author kys
 *
 */
@Mapper
public interface MemberMapper {

	// 테스트 맴버리스트 불러오기
	public List<MemberVo> getList();

}
