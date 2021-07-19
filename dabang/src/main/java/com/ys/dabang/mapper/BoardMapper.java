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

	// 게스글 리스트 불렁기
	public List<BoardVo> getList();

	// 베스트 게시글 불러오기
	public List<BoardVo> bestList();

}
