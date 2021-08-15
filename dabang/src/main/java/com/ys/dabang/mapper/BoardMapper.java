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

	// 게시글 들고오기
	public List<Map<String, Object>> getList(Map param);

	// 베스트 게시글 들고오기
	public List<Map<String, Object>> bestList();

	// 게시글 정보 들고오기
	public List<Map<String, Object>> boardInfo(Map param);

	// 댓글 정보 들고오기
	public List<Map<String, Object>> repleInfo(Map param);

	// 댓글 입력
	public int repleInsert(Map param);

}
