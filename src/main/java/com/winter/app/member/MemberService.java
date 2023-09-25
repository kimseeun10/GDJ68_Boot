package com.winter.app.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberService implements UserDetailsService {

	//DAO 
	@Autowired
	private MemberDAO memberDAO; //아이디 중복 검사를 위해
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("=========로그인 시도 중==========");
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(username);
		
		try {
			memberVO = memberDAO.getMember(memberVO);
		} catch (Exception e) {
			
			e.printStackTrace();
			memberVO=null;
		}
		
		return memberVO;
	}
	
	//로그인 메서드
//	public MemberVO getLogin(MemberVO memberVO)throws Exception{
//		MemberVO loginVO = memberDAO.getMember(memberVO); // username으로 조회
//		
//		if(loginVO == null) {
//			return loginVO;
//		}
//		
//		if(loginVO.getPassword().equals(memberVO.getPassword())) {
//			return loginVO;
//		}
//		
//		return null;
//	}
	
	
	//검증 메서드
	public boolean getMemberError(MemberVO memberVO, BindingResult bindingResult)throws Exception{
		boolean check = false; // false면 error가 없다, true면 error가 있다 (검증실패)
		
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

	@Transactional(rollbackFor = Exception.class)
	public int setJoin(MemberVO memberVO)throws Exception{
		
		memberVO.setPassword(passwordEncoder.encode(memberVO.getPassword()));
		int result = memberDAO.setJoin(memberVO);
		Map<String, Object> map = new HashMap<>();
		map.put("roleNum", 3);
		map.put("username", memberVO.getUsername());
		result = memberDAO.setMemberRole(map);
		
		return result;
	}
	
}
