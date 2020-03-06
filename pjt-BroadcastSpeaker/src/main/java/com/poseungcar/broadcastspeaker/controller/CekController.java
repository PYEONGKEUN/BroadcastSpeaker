package com.poseungcar.broadcastspeaker.controller;


import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.VO.MyExtensionMessage;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;
import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;



/*
 * 
 * CEK의 요청을 처리하기위한 컨트롤러
 * 각 메소드의 이름은 
 */
@Controller
public class CekController {


	@Autowired
	ICallsVoMapService callsVoMapService;


	@Autowired
	ITtsService ttsService;

	/**
	 * Simply selects the home view to render by returning its name.
	 *  
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/cek", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody public ResponseEntity<MyExtensionMessage> Call (
			@RequestBody Map<String,Object> map,
			HttpSession session) throws Exception	{

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

	public MyExtensionMessage IntentRequest(Map<String,Object> map, HttpSession session) throws Exception {
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
		if (intentName.equals("Call")) { 
			if (slots != null) { 
				// 인터렉션 모델에서의 슬롯 이름과 같아야 한다.
				String msg = "";
				Map<String, Object> placesMap = null;
				Map<String, Object> namesMap = null;
				Map<String, Object> recievedSlots = new HashMap<String, Object>();

				//현재 받은 SLOT값들을 전달하면서 재호출
				if(!slots.containsKey("PLACE")) {
					msg ="방송할 위치를 알려주세요.";
					//전달된 SLOT을 CEK에게 다시 전달
					namesMap = (HashMap<String, Object>) slots.get("NUMBER");
					numberValue = (String) namesMap.get("value");					
					recievedSlots.put("NUMBER", numberValue);
					result= new MyExtensionMessage("Call", msg, false, "PlainText",recievedSlots);
					//현재 받은 SLOT값들을 전달하면서 재호출
				}else if(!slots.containsKey("NUMBER")) {					
					msg ="수리완료된 차량번호를 알려주세요.";
					//전달된 SLOT을 CEK에게 다시 전달
					placesMap = (HashMap<String, Object>) slots.get("PLACE");				
					placesValue = (String) placesMap.get("value");
					recievedSlots.put("PLACE", placesValue);
					result= new MyExtensionMessage("Call", msg, false, "PlainText",placesMap);
				}else {

					
					placesMap = (HashMap<String, Object>) slots.get("PLACE");				
					placesName = (String) placesMap.get("name"); 
					placesValue = (String) placesMap.get("value");
					
					namesMap = (HashMap<String, Object>) slots.get("NUMBER");
					numberName = (String) namesMap.get("name");
					numberValue = (String) namesMap.get("value");
					
					//인식된 SLOT으로부터 값 추출
					msg = numberValue +"번 차량 수리 완료되었습니다.";
					result= new MyExtensionMessage("Call", msg, true, "PlainText");
					String fileName = ttsService.downloadMP3(msg,session, placesValue);
					// 테스트용 아이디
					callsVoMapService.offer("skvudrms54", placesValue, numberValue,fileName);
					return result;
				}					
			} 		
			// 다시 물을때   
		}else if (intentName.equals("AddInfo")) { 
			if (slots != null) { 
				// 인터렉션 모델에서의 슬롯 이름과 같아야 한다.
				String msg = "";
				Map<String, Object> placesMap = null;
				Map<String, Object> namesMap = null;
				Map<String, Object> recievedSlots = new HashMap<String, Object>();

				if(!slots.containsKey("PLACE")) {
					msg ="방송할 위치를 알려주세요.";
					
					namesMap = (HashMap<String, Object>) slots.get("NUMBER");
					numberValue = (String) namesMap.get("value");					
					recievedSlots.put("NUMBER", numberValue);
					//현재 받은 SLOT값들을 전달하면서 재호출
					result= new MyExtensionMessage("Call", msg, false, "PlainText",namesMap);
				}else if(!slots.containsKey("NUMBER")) {
					msg ="수리완료된 차량번호를 알려주세요.";
					//전달된 SLOT을 CEK에게 다시 전달
					placesMap = (HashMap<String, Object>) slots.get("PLACE");				
					placesValue = (String) placesMap.get("value");
					recievedSlots.put("PLACE", placesValue);
					placesMap = (HashMap<String, Object>) slots.get("PLACE");
					//현재 받은 SLOT값들을 전달하면서 재호출
					result= new MyExtensionMessage("Call", msg, false, "PlainText",placesMap);
				}else {
				
					placesMap = (HashMap<String, Object>) slots.get("PLACE");				
					placesName = (String) placesMap.get("name"); 
					placesValue = (String) placesMap.get("value");
					
					namesMap = (HashMap<String, Object>) slots.get("NUMBER");
					numberName = (String) namesMap.get("name");
					numberValue = (String) namesMap.get("value");
					
					//인식된 SLOT으로부터 값 추출
					msg = numberValue +"번 차량 수리 완료되었습니다.";
					result= new MyExtensionMessage("Call", msg, true, "PlainText");
					String fileName = ttsService.downloadMP3(msg,session, placesValue);
				
					// 테스트용 아이디
					callsVoMapService.offer("skvudrms54", placesValue, numberValue,fileName);
					
					return result;
				}					
			}
		} 
		// Built-in Intent	처리
		else if (intentName.equals("Clova.YesIntent")) { 
			result = new MyExtensionMessage(intentName, "예 라고 하셨나요?", true, "PlainText");
		} else if (intentName.equals("Clova.NoIntent")) { 
			result = new MyExtensionMessage(intentName, "아니오 라고 하셨나요?", true, "PlainText");
		} else if (intentName.equals("Clova.GuideIntent")) {
			result = new MyExtensionMessage("hearTestIntent", "어디에서 몇번 차량 수리완료 라고 말해주세요.", false, "PlainText");
		} else if (intentName.equals("Clova.CancelIntent")) { 
			result = new MyExtensionMessage("hearTestIntent", "포승 스피커를 종료합니다.", true, "PlainText");
		}

		return result;
	}

}
