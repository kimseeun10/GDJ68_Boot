package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.winter.app.board.BoardVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	//ModelAndView , void, String
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception{
//		List<BoardVO> ar = noticeService.getList(pager);
//		model.addAttribute("list", ar);
		//error, warn(경고 에러발생할 것 같을 때), info, debug, trace
		log.error("getList 실행");
		return "board/list";
	}
	
}
