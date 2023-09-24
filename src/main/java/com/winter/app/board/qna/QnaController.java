package com.winter.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.board.BoardVO;
import com.winter.app.board.FileVO;
import com.winter.app.commons.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/qna/*")
@Slf4j
public class QnaController {

	@Autowired
	private QnaService qnaService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "qna";
	}
	
	@GetMapping("fileDown")
	public String getFileDown(FileVO fileVO, Model model) throws Exception{
		fileVO = qnaService.getFileDetail(fileVO);
		model.addAttribute("fileVO", fileVO);
		return "fileDownView";
	}
	
	@GetMapping("list")
	public String getList(Pager pager, Model model) throws Exception{
		List<BoardVO> ar = qnaService.getList(pager);
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
	public String add(QnaVO qnaVO, MultipartFile [] files)throws Exception{
		
		int result = qnaService.add(qnaVO, files);
		return "redirect:./list";
	}
	
	@GetMapping("detail")
	public String getDetail(BoardVO boardVO, Model model)throws Exception{
		boardVO= qnaService.getDetail(boardVO);
		model.addAttribute("vo",boardVO);
		
		return "board/detail";
	}
	
	@GetMapping("update")
	public String setUpdate(BoardVO boardVO, Model model) throws Exception{
		boardVO = qnaService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		return "board/update";
	}
	
	@PostMapping("update")
	public String setUpdate(BoardVO boardVO) throws Exception{
		int result = qnaService.setUpdate(boardVO);
		
		return "redirect:./list";
	}
	
	@GetMapping("delete")
	public String setDelete(BoardVO boardVO) throws Exception{
		int result = qnaService.setDelete(boardVO);
		return "redirect:./list";
	}
	
}
