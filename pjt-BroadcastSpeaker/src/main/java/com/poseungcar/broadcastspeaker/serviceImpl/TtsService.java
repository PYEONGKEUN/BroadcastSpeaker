package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseungcar.broadcastspeaker.VO.MyExtensionMessage;
import com.poseungcar.broadcastspeaker.service.IPlaceHan2EnService;
import com.poseungcar.broadcastspeaker.service.ITtsService;

@Service
public class TtsService implements ITtsService{
	
	@Autowired
	IPlaceHan2EnService placeHan2EnService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/extension", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody public ResponseEntity<MyExtensionMessage> call (@RequestBody Map<String, Object> map, HttpSession session)	{
		Map<String, Object> m = (HashMap<String, Object>)map.get("request"); 
		String type = (String) m.get("type");
		MyExtensionMessage mm = null;
		if(type.equals("LaunchRequest"))
		{
			// extension 시작
			mm = new MyExtensionMessage("turnOnIntent", "스마트 방송스피커를 시작합니다. 사람을 불러주세요 ", false, "PlainText"); 
		}
		else if (type.equals("IntentRequest")) { 
			// extension의 인텐트 시작
			Map<String, Object> intent = (HashMap<String, Object>) m.get("intent"); 
			String intentName = (String) intent.get("name");
			Map<String, Object> slots = (HashMap<String, Object>) intent.get("slots");
			String placesName = ""; 
			String placesValue = "";
			String numberName = ""; 
			String numberValue = "";
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
				mm= new MyExtensionMessage("call", "차량번호 "+numberValue +"번 소유주님 " +placesValue + "로 와주세요.", true, "PlainText");
				String msg = "차량번호 "+numberValue +"번 소유주님 " +placesValue + "로 와주세요.";
				handleCallsVoNttsMP3(msg,session, placesValue);
				// Built-in Intent		처리
			} else if (intentName.equals("Clova.YesIntent")) { 
				mm = new MyExtensionMessage(intentName, "예 라고 하셨나요?", true, "PlainText");
			} else if (intentName.equals("Clova.NoIntent")) { 
				mm = new MyExtensionMessage(intentName, "노 라고 하셨나요?", true, "PlainText");

			} else if (intentName.equals("Clova.GuideIntent")) {
				mm = new MyExtensionMessage("hearTestIntent", "1번 손님 사장실로 불러줘. 라고 해보세요.", false, "PlainText");
			} else if (intentName.equals("Clova.CancelIntent")) { 
				mm = new MyExtensionMessage("hearTestIntent", "스마트 방송 스피커 실행을 취소합니다.안녕", true, "PlainText");

			}
		} else if (type.equals("SessionEndedRequest")){
			// extension 종료
			mm = new MyExtensionMessage("turnOnIntent", "스마트 방송 스피커를 종료합니다.", false, "PlainText");
		}
		return new ResponseEntity<MyExtensionMessage>(mm, HttpStatus.OK);
	}
	
	
}
