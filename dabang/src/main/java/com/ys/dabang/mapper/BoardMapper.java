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

	// �Խ��� ����Ʈ �ҷ�����
	public List<Map<String, Object>> getList();

	// ����Ʈ �Խñ� �ҷ�����
	public List<Map<String, Object>> bestList();

	// �� �� ���� ������
	public List<Map<String, Object>> boardInfo(Map param);

	// ��� ������ ����
	public List<Map<String, Object>> repleInfo(Map param);

	// 댓글 입력
	public int repleInsert(Map param);

}
