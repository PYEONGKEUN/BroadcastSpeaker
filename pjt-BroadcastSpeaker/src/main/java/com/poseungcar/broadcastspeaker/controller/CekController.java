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
import com.poseungcar.broadcastspeaker.status.CallsVoMap;
import com.poseungcar.broadcastspeaker.util.TimeLib;

@Controller
public class CekController {


	@Autowired
	IPlaceHan2EnService placeHan2EnService;

	//callsVO 큐에 파일이름(경로 X)을 추가하고 TTS를 다운 로드
	public String handleCallsVoNttsMP3(String msg, HttpSession session, String han) {
		String clientId = "quktzpx0ng";//애플리케이션 클라이언트 아이디값";
		String result = "";
		String clientSecret = "JYlINeOp2s6fNfvxNCLE1IIihw4yyYcXXCxnEltX";//애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode(msg, "UTF-8"); // 13자
			String apiURL = "https://naveropenapi.apigw.ntruss.com/voice-premium/v1/tts";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			// post request
			String postParams = "speaker=nara&volume=0&speed=1&pitch=0&emotion=0&format=mp3&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				// 시간으로 이름 생성
				String tempname = TimeLib.getCurrDateTime();
				//큐에 저장
				//queue.offer(tempname);

				// 세션에서 현재 로그인중인 아이디의 정보를 가져온다.
				// 공통 공유 자원 CallsVoMap.userCallsVos 에서 해당 계정의 CallsVO 객체를 가져온다
				//CallsVO에서 큐 객체를 가지고 처리한다.
				Member member = (Member)session.getAttribute("memberInfo");
				CallsVO callsVO =CallsVoMap.userCallsVos.get(member.getMem_id());
				placeHan2EnService.offer(callsVO, han, tempname);	                


				File f = new File("/opt/clovatest/"+tempname + ".mp3");
				result  = tempname;
				f.createNewFile();
				OutputStream outputStream = new FileOutputStream(f);
				while ((read =is.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				is.close();
			} else {  // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = br.readLine()) != null) {
					response.append(inputLine);
				}
				br.close();
				System.out.println(response.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return result;
	}


	@ResponseBody
	@PostMapping(value = "/cekhandler", consumes = "application/json", produces = "application/json")
	public Map<String, Boolean> cekHandler(   
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
		String tempname = handleCallsVoNttsMP3(msg,session,placesValue);

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
