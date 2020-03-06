package com.poseungcar.broadcastspeaker.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.util.CekMsg;

@RestController
public class ClientController {
	
	@Autowired
	ICallsVoMapService callsVoMapService;
	
	@Autowired
	ITtsService ttsService;


	@PostMapping(value = "/transmit", consumes = "application/json", produces = "application/json")
	@ResponseBody public Map<String, Boolean> transmitHandler(   
			HttpServletRequest request, 
			HttpSession session, 
			@RequestBody Map<String, String> map) throws Exception {		

		Map<String, Boolean> result = new HashMap<String, Boolean>();

		//메시지 생성
		if(map.containsKey("id") || map.containsKey("place") || map.containsKey("number")) {
			result.put("complete", false);
		}
		String id = (String) map.get("id");
		String place = (String) map.get("place");
		String number = (String) map.get("number");		
		String msg = CekMsg.msg(number);
		//파일명 경로x 확장자 x
		String fileName = ttsService.downloadMP3(msg,session,place);

		callsVoMapService.offer(id, place, number, fileName);

		result.put("complete", true);
		return result;

	}
	
	
	
	@PostMapping(value = "/request", consumes = "application/json", produces = "application/json")
	@ResponseBody public  Map<String, String> requestHandler ( 
			@RequestBody Map<String, String> map,
			HttpSession session
			) throws Exception{
		
		Map<String, String> result = null;
		String id = (String) map.get("id");
		String place = (String) map.get("place");
		
		
		result = callsVoMapService.poll(id, place);

        return result;
		
	}
}
