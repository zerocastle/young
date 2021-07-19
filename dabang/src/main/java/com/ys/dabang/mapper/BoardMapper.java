/**
 * 
 */
package com.ys.dabang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ys.dabang.vo.BoardVo;
import com.ys.dabang.vo.MemberVo;

/**
 * @author kys
 *
 */
@Mapper
public interface BoardMapper {

	// �Խ��� ����Ʈ �ҷ���
	public List<BoardVo> getList();

	// ����Ʈ �Խñ� �ҷ�����
	public List<BoardVo> bestList();

}
