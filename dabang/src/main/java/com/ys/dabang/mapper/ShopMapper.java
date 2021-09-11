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
public interface ShopMapper {

	// 게시글 들고오기
	public List<Map<String, Object>> getList(Map param);

}
