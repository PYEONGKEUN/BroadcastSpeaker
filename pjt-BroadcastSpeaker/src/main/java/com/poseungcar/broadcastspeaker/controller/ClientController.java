package com.poseungcar.broadcastspeaker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.IPlaceHan2EnService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;

public class ClientController {
	
	@Autowired
	IPlaceHan2EnService placeHan2EnService;
	
	@Autowired
	ITtsService ttsService;


	@ResponseBody
	@PostMapping(value = "/transmit", consumes = "application/json", produces = "application/json")
	public Map<String, Boolean> transmitHandler(   
			HttpServletRequest request, 
			HttpSession session, 
			@RequestBody Map<String, Object> map) throws Exception {		

		Map<String, Boolean> result = new HashMap<String, Boolean>();

		//메시지 생성
		String id = (String) map.get("id");
		String placesValue = (String) map.get("place");
		String numberValue = (String) map.get("number");		
		String msg = "차량번호 "+numberValue +"번 소유주님 " +placesValue + "로 와주세요.";
		//파일명 경로x 확장자 x
		String tempname = ttsService.downloadMP3(msg,session,placesValue);

		//CallVos에 추가 
		//Member member = (Member)session.getAttribute("memberInfo");
		//CallsVO callsVO =CallsVoMap.userCallsVos.get(member.getMem_id());
		if(!CallsVoMap.userCallsVos.containsKey(id)) {			
			return null; 
		}
		CallsVO callsVO =CallsVoMap.userCallsVos.get(id);
		placeHan2EnService.offer(callsVO, placesValue, tempname);

		result.put("complete", true);
		return result;

	}
	
	
	
	@PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
	public Map<String, String> requestHandler ( 
			@RequestBody Map<String, Object> map,
			HttpSession session
			) throws Exception{
		
		Map<String, String> result = new HashMap<String, String>();
		// 큐로 부터 파일 이름을 호출
		//String fileName = queue.poll();
		String id = (String) map.get("id");
		String place = (String) map.get("place");
		
		
		
        //Member member = (Member)session.getAttribute("memberInfo");
        //CallsVO callsVO =CallsVoMap.userCallsVos.get(member.getMem_id());
		if(!CallsVoMap.userCallsVos.containsKey(id)) {			
			return null; 
		}
		
		CallsVO callsVO =CallsVoMap.userCallsVos.get(id);
        String fileName = placeHan2EnService.poll(callsVO, place);
        return result;
		
	}
}
