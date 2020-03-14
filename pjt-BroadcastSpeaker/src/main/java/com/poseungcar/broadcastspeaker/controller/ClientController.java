package com.poseungcar.broadcastspeaker.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseungcar.broadcastspeaker.DAO.IGroupMemberDAO;
import com.poseungcar.broadcastspeaker.DAO.IMemberDAO;
import com.poseungcar.broadcastspeaker.DTO.GroupMember;
import com.poseungcar.broadcastspeaker.DTO.Member;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.service.IClientService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.util.CPVMsg;

@RestController
public class ClientController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	ICallsVoMapService callsVoMapService;
	
	@Autowired
	ITtsService ttsService;
	
	@Autowired
	IGroupMemberDAO groupMemberDAO;

	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	IClientService clientService;

	// Client로부터 방송 정보 수신
	// 번호 정보와 파일이름리턴 
	
	
	@PostMapping(value = "/transmit", consumes = "application/json", produces = "application/json")
	@ResponseBody public Map<String, String> transmitHandler(   
			HttpServletRequest request, 
			HttpSession session, 
			@RequestBody Map<String, String> map) throws Exception {		

		Map<String, String> result = new HashMap<String, String>();

		//메시지 생성
		if(map.get("type").toString().equals("number")) {
			if(!map.containsKey("id") || !map.containsKey("place") || !map.containsKey("number")) {
				result.put("status", "fail");
			}
			String id = (String) map.get("id");
			String place = (String) map.get("place");
			String number = (String) map.get("number");		
			
			if(clientService.callCarNumber(id, place, number)) {
				result.put("status", "complete");
			}else {
				result.put("status", "fail");
			}

			
		}else if(map.get("type").toString().equals("name")) {
			if(!map.containsKey("id") || !map.containsKey("place") || !map.containsKey("name")) {
				result.put("status", "fail");
			}
			String id = (String) map.get("id");
			String place = (String) map.get("place");
			String grpMemName = (String) map.get("name");		
			
			if(clientService.callGrpMemName(id, place, grpMemName)){
				result.put("status", "complete");
			}else {
				result.put("status", "fail");
			}
		}
		
		logger.info(result.toString());
		return result;

	}
	
	
	// 요청 계정정보와 방송위치 입력시
	// 번호 정보와 파일이름리턴 
	@PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
	@ResponseBody public  Map<String, String> requestHandler ( 
			@RequestBody Map<String, String> map,
			HttpSession session
			) throws Exception{
		
		Map<String, String> result = null;
		String id = (String) map.get("id");
		String place = (String) map.get("place");
		
		try {
			result = callsVoMapService.poll(id, place);
			result.put("status", "complete");
		}catch(NullPointerException e) {
			result = new HashMap<String, String>();
			result.put("status", "fail");
			result.put("msg" , id+" has no msg in "+place);
		}
		
		logger.info(result.toString());
        return result;
		
	}
	
	@PostMapping(value = "/getgrpmem", consumes = "application/json", produces = "application/json")
	@ResponseBody public  Map<String, Object> getGroupMember ( 
			@RequestBody Map<String, String> map,
			HttpSession session
			) throws Exception{
		
		Map<String, Object> result = new HashMap<String, Object> ();
		String id = (String) map.get("id");	
		
		//Member 에서 해당 아이디의 그룹을 가져옴		
		Member member = new Member();
		member.setMemId(id);
		
		List<Member> seletedMem = memberDAO.select(member);
		//해당 아이디가 없을경우 종료하고 status 리턴
		if(seletedMem == null) result.put("status", "fail");
		
		//Member 에서 가져온 그룹넘버로 그룹에 속한 스룹원들의 이름을 가져옴
		int grpNo = seletedMem.get(0).getGrpNo();		
		GroupMember groupMember= new GroupMember();
		groupMember.setGrpNo(grpNo);
		
		//Member 에서 해당 아이디의 그룹을 가가져와서 전달
		List<GroupMember> selectedGroupMember= groupMemberDAO.select(groupMember);
		if(selectedGroupMember == null) {
			result.put("status", "fail");
		}else {
			result.put("status", "complete");
			result.put("array", selectedGroupMember);
		}
		
		
		logger.info(result.toString());
        return result;
		
	}
}
