package com.winter.app.board.notice;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.winter.app.board.BoardDAO;

// @Repository 안써도 상관없음 단, mapper는 꼭 들어가야 함
@Mapper
public interface NoticeDAO extends BoardDAO{
	
}
