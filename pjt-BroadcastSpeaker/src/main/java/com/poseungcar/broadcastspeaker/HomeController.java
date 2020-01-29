package com.poseungcar.broadcastspeaker;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.poseungcar.broadcastspeaker.VO.MyExtensionMessage;



/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/extension", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody public ResponseEntity<MyExtensionMessage> weather (@RequestBody Map<String, Object> map)	{
		Map m = (HashMap)map.get("request"); 
		String type = (String) m.get("type");
		MyExtensionMessage mm = null;
		if(type.equals("LaunchRequest"))
		{
			// extension 시작
			mm = new MyExtensionMessage("turnOnIntent", "안녕 짱구 박사를 시작합니다. 제어하려는 전등을 말씀 하세요. ", false, "PlainText"); 
		}
		else if (type.equals("IntentRequest")) { 
			// extension의 인텐트 시작
			Map intent = (HashMap) m.get("intent"); 
			String intentName = (String) intent.get("name");
			Map slots = (HashMap) intent.get("slots");
			String slotName = ""; 
			String slotValue = "";
			if (intentName.equals("turnOnIntent"))
			{ 
				if (slots != null) { 
					Map myslot = (HashMap) slots.get("homeSlotType"); 
					slotName = (String) myslot.get("name"); 
					slotValue = (String) myslot.get("value"); System.out.println("slotName===" + slotName); 
					System.out.println("slotValue===" + slotValue); 
				} mm= new MyExtensionMessage("turnOnIntent", slotValue + "의 전등을 켰습니다. ", true, "PlainText");

				// Built-in Intent		처리
			} else if (intentName.equals("Clova.YesIntent")) { 
				mm = new MyExtensionMessage(intentName, "예 라고 하셨나요?", true, "PlainText");
			} else if (intentName.equals("Clova.NoIntent")) { 
				mm = new MyExtensionMessage(intentName, "노 라고 하셨나요?", true, "PlainText");

			} else if (intentName.equals("Clova.GuideIntent")) {
				mm = new MyExtensionMessage("hearTestIntent", "부산 날씨는 어때라고 해보세요", false, "PlainText");
			} else if (intentName.equals("Clova.CancelIntent")) { 
				mm = new MyExtensionMessage("hearTestIntent", "짱구 박사 실행을 취소합니다.안녕", true, "PlainText");

			}
		} else if (type.equals("SessionEndedRequest")){
			// extension 종료
			mm = new MyExtensionMessage("turnOnIntent", "짱구 박사를 종료합니다.", false, "PlainText");
		}
		return new ResponseEntity<MyExtensionMessage>(mm, HttpStatus.OK);
	}

}

