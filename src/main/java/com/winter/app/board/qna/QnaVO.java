package com.winter.app.board.qna;

import java.util.List;

import com.winter.app.board.BoardVO;
import com.winter.app.board.notice.NoticeFileVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaVO extends BoardVO{

	private Long ref;
	private Long step;
	private Long depth;
	private List<NoticeFileVO> list;
}
