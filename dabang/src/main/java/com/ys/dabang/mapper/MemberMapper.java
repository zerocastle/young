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

	// �׽�Ʈ �ɹ�����Ʈ �ҷ�����
	public List<MemberVo> getList();

}
