package com.winter.app.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class MemberService {

	//DAO 
	@Autowired
	private MemberDAO memberDAO; //아이디 중복 검사를 위해
	
	//로그인 메서드
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		MemberVO loginVO = memberDAO.getMember(memberVO); // username으로 조회
		
		if(loginVO == null) {
			return loginVO;
		}
		
		if(loginVO.getPassword().equals(memberVO.getPassword())) {
			return loginVO;
		}
		
		return null;
	}
	
	
	//검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check = true; // false면 error가 없다, true면 error가 있다 (검증실패)
		
		//password 일치여부 검증
		if(!memberVO.getPassword().equals(memberVO.getPasswordCheck())) {
			check = true; // check != check
			
			bindingResult.rejectValue("passwordCheck", "memberVO.password.equalCheck");
			
		}
		
		// 아이디 중복 검사
		MemberVO checkVO = memberDAO.getMember(memberVO);
		
		if(checkVO != null) {
			check = true;
			bindingResult.rejectValue("username", "memberVO.username.equalCheck");
		}
		
		return check;
	}
	
	
	
}
