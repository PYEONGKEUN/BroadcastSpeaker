package com.poseungcar.broadcastspeaker.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseungcar.broadcastspeaker.DTO.Member;
import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.VO.MyExtensionMessage;
import com.poseungcar.broadcastspeaker.service.IPlaceHan2EnService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;
import com.poseungcar.broadcastspeaker.util.TimeLib;


/*
 * 
 * CEK의 요청을 처리하기위한 컨트롤러
 * 각 메소드의 이름은 
 */
@Controller
public class CekController {


	@Autowired
	IPlaceHan2EnService placeHan2EnService;


	@Autowired
	ITtsService ttsService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cek", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody public ResponseEntity<MyExtensionMessage> call (
			@RequestBody Map<String,Object> map,
			HttpSession session)	{

		Map<String, Object> m = (HashMap<String, Object>)map.get("request"); 
		String type = (String) m.get("type");
		MyExtensionMessage mm = null;
		
		if(type.equals("LaunchRequest")){
			// extension 시작	
			mm = new MyExtensionMessage("turnOnIntent", "포승 스피커를 시작합니다.", false, "PlainText"); 
		}
		else if (type.equals("IntentRequest")) {
			// 
			mm = IntentRequest(m, session);
		} else if (type.equals("SessionEndedRequest")){
			// extension 종료		
			mm = new MyExtensionMessage("turnOnIntent", "포승 스피커를 종료합니다.", false, "PlainText");
		}
		return new ResponseEntity<MyExtensionMessage>(mm, HttpStatus.OK);
	}

	public MyExtensionMessage IntentRequest(Map<String,Object> map, HttpSession session) {
		// extension의 인텐트 시작
		Map<String, Object> intent = (HashMap<String, Object>) map.get("intent"); 
		String intentName = (String) intent.get("name");
		Map<String, Object> slots = (HashMap<String, Object>) intent.get("slots");
		String placesName = ""; 
		String placesValue = "";
		String numberName = ""; 
		String numberValue = "";

		MyExtensionMessage result = null;
		// custom Intent
		if (intentName.equals("call")) { 
			if (slots != null) { 
				// 인터렉션 모델에서의 슬롯 이름과 같아야 한다.
				Map<String, Object> placesMap = (HashMap<String, Object>) slots.get("PLACES");
				Map<String, Object> namesMap = (HashMap<String, Object>) slots.get("NUMBER"); 					
				placesName = (String) placesMap.get("name"); 
				placesValue = (String) placesMap.get("value"); 
				numberName = (String) namesMap.get("name");
				numberValue = (String) namesMap.get("value");
			} 
			
			//인식된 SLOT으로부터 값 추출
			String msg = numberValue +"번 차량 수리 완료되었습니다.";
			result= new MyExtensionMessage("call", msg, true, "PlainText");
			ttsService.downloadMP3(msg,session, placesValue);			
			
			
			// Built-in Intent	처리
		} else if (intentName.equals("Clova.YesIntent")) { 
			result = new MyExtensionMessage(intentName, "예 라고 하셨나요?", true, "PlainText");
		} else if (intentName.equals("Clova.NoIntent")) { 
			result = new MyExtensionMessage(intentName, "아니오 라고 하셨나요?", true, "PlainText");
		} else if (intentName.equals("Clova.GuideIntent")) {
			result = new MyExtensionMessage("hearTestIntent", "1234번 차주님 차량 수리 완료되었습니다.", false, "PlainText");
		} else if (intentName.equals("Clova.CancelIntent")) { 
			result = new MyExtensionMessage("hearTestIntent", "포승 스피커를 종료합니다.", true, "PlainText");
		}

		return result;
	}

}
