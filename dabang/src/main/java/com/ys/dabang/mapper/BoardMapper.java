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

	// 게스글 리스트 불렁기
	public List<BoardVo> getList();

	// 베스트 게시글 불러오기
	public List<BoardVo> bestList();

	// 글 상세 정보 들고오기
	public List<BoardVo> boardInfo(Map param);

	// 댓글 가지고 오기
	public List<BoardVo> repleInfo(Map param);

}
