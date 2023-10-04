package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {

	//service
	@Autowired
	MemberService memberService = new MemberService();
	
	@GetMapping("info")
	public void getInfo(HttpSession session)throws Exception{
		//1. DB에서 사용자 정보를 조회해서 JSP로 보내는 방법
		
	}
	
	@GetMapping("update")
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO, Model model)throws Exception{
//		MemberVO memberVO = (MemberVO)principal;
//		memberVO = memberService.getLogin(memberVO); //비번까지 같이 검사됨
		
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		model.addAttribute("memberInfoVO", memberInfoVO);
	}

	@PostMapping("update")
	public String setUpdate(@Valid MemberInfoVO memberInfoVO, BindingResult bindingResult)throws Exception{
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO memberVO = (MemberVO)obj;
		memberVO.setEmail("UpdateEmail@naver.com");
		
		return "redirect:/";
	}
	
	@GetMapping("kakaoLogout")
	public String kakaoLogout() {
		log.info("**********카카오 로그아웃************");
		return "redirect:../";
	}
	
//	@GetMapping("logout")
//	public String getLogout(HttpSession session)throws Exception{
//		session.invalidate(); //일정시간 지나면 로그아웃
//		
//		return "redirect:../";
//	}
	
//	@GetMapping("login")
//	public void getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
//		
//	}
	
	@GetMapping("login")
	public String getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
		SecurityContext context = SecurityContextHolder.getContext();
		
		String check = context.getAuthentication().getPrincipal().toString();
		
		log.info("======= Context : {} =========", context.getAuthentication().getPrincipal().toString());
		
		if(!check.equals("anonymousUser")) {
			return "redirect:/";
		}
		return "member/login";
	}
	
	
//	@PostMapping("login")
//	public String getLogin2(MemberVO memberVO, HttpSession session)throws Exception{
//		memberVO = memberService.getLogin(memberVO);
//		
//		if(memberVO != null) {
//			session.setAttribute("member", memberVO);
//			return "redirect:../";
//		}
//		
//		return "./login";
//		
//	}
	
	
//	@GetMapping("join")
//	public void setJoin(Model model)throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);
//	}
	
// 위,아래 동일한 코드
	
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{

	}
	
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile photo)throws Exception{
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		if(bindingResult.hasErrors() || check) {
			return "member/join";
		}
		
		//회원가입 진행
		int result = memberService.setJoin(memberVO);
		
		log.info("photo : {} --- size :{}", photo.getOriginalFilename(), photo.getSize());
		return "redirect:../";
		
	}
	
	
	
}
