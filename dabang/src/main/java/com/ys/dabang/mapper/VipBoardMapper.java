/**
 * 
 */
package com.ys.dabang.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author kys
 *
 */
@Mapper
public interface VipBoardMapper {

	// �Խ��� ����Ʈ �ҷ�����
	public List<Map<String,Object>> getList();

}
