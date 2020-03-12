package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poseungcar.broadcastspeaker.DAO.IMemberDAO;
import com.poseungcar.broadcastspeaker.DTO.Member;
import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.IMemberService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;

/**
 * LoginService
 */
@Service
public class MemberService implements IMemberService{
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

	@Autowired
	private IMemberDAO memberDAO;
	//로그인 서비스
	@Override
	public boolean login(Member member, HttpSession session) {
		// TODO Auto-generated method stub

		boolean result = false;
		
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //member.setMem_pw(passwordEncoder.encode(member.getMem_pw()));


		if(member.getMemId() == null || member.getMemPassword() == null){

		}else{

			List<Member> output =  memberDAO.select(member);
			// 아이디와 비밀번호를 
			if(output.size() > 0){
				logger.info(member.getMemId() +" logged in");
				session.setAttribute("memberInfo", output.get(0));
				session.setAttribute("status", "login");
				// 공통 변수에 추가 현재 로그인 사용자의 CallsVO가 없을경우 하나 생성
				if(!CallsVoMap.userCallsVos.containsKey(output.get(0).getMemId())) {
					CallsVO callsVO = new CallsVO();
					callsVO.setUserId(output.get(0).getMemId());
					CallsVoMap.userCallsVos.put(output.get(0).getMemId(), callsVO);
				}						

				
				result = true;
			}else {
				logger.info(member.getMemId() +"Password or Id is not correct");
			}

		}


		return result;
	}
	//세션에서 로그인 정보를 삭제
	@Override
	public boolean logout(HttpSession session) {
		// TODO Auto-generated method stub
		boolean result = false;
		//session.removeAttribute("memberInfo");
		try{
			session.invalidate();
			result = true;
		}catch (Exception e){
			logger.info(e.toString());
		}

		return result;
	}
	// session에서 로그인 정보를 확인
	@Override
	public boolean isLogin(HttpSession session) {
		// TODO Auto-generated method stub
		// 세션에 멤버 정보가 있다면 로그인 상태
		// 아니라면 로그아웃 상태
		
		if(session.getAttribute("memberInfo")!=null) {
			return true;
		}else {
			return false;
		}


	}
	@Override
	public boolean idIsExist(Member member) {
		// TODO Auto-generated method stub

		
		List<Member> output = memberDAO.select(member);
		
		
		if(output.size() > 0) {
			return true;
		}else {
			return false;
		}
					
		
	}
	@Override
	public boolean loginCookie(Member member, Cookie cookie) {
		
		// TODO Auto-generated method stub

				boolean result = false;


				if(member.getMemId() == null || member.getMemPassword() == null){

				}else{

					List<Member> output =  memberDAO.select(member);
					// 아이디와 비밀번호를 
					if(output.size() > 0){
						logger.info(member.getMemId() +" logged in");
						
						cookie.setValue(member.getMemId());					
						result = true;
					}else {
						logger.info(member.getMemId() +"Password or Id is not correct");
					}

				}


				return result;
	}
	@Transactional
	public int joinUser(Member member) {
		// 비밀번호 암호화
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //member.setMem_pw(passwordEncoder.encode(member.getMem_pw()));
        memberDAO.insert(member);
        
        return memberDAO.insert(member);
	}








}
