package com.winter.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.board.BoardService;
import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "notice";
	}
	
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO, Model model) throws Exception{
		fileVO = noticeService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);
		return "fileDownView";
	}
	
	//ModelAndView , void, String
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception{
		List<BoardVO> ar = noticeService.getList(pager);
		model.addAttribute("list", ar);
		model.addAttribute("pager", pager);
		//error, warn(경고 에러발생할 것 같을 때), info, debug, trace
		log.error("getList 실행");
		return "board/list";
	}
	
	@GetMapping("add")
	public String add() throws Exception{
		return "board/add";
	}
	
	@PostMapping("add")
	public String add(NoticeVO noticeVO, MultipartFile [] files)throws Exception{
		log.info("files : {}", files);
		
		int result = noticeService.add(noticeVO, files);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model)throws Exception{
		boardVO= noticeService.getDetail(boardVO);
		model.addAttribute("vo",boardVO);
		
		return "board/detail";
	}
	
	//json 사용
	
//	@GetMapping("detail2")
//	@ResponseBody
//	public BoardVO getDetail(NoticeVO noticeVO)throws Exception{
//		return noticeService.getDetail(noticeVO);
//	}
	
	@GetMapping("update")
	public String setUpdate(BoardVO boardVO, Model model) throws Exception{
		boardVO = noticeService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO) throws Exception{
		int result = noticeService.setUpdate(boardVO);
		
//		return "redirect:./detail?boardNo="+boardVO.getBoardNo();
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO) throws Exception{
		int result = noticeService.setDelete(boardVO);
		return "redirect:./list";
	}
}
