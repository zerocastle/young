/**
 * 
 */
package com.ys.dabang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ys.dabang.vo.BoardVo;

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

	// �� �� ���� ������
	public List<BoardVo> boardInfo(Map param);

	// ��� ������ ����
	public List<BoardVo> repleInfo(Map param);

}
