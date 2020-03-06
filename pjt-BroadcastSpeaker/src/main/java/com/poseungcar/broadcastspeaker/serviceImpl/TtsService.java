package com.poseungcar.broadcastspeaker.serviceImpl;

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

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
 * tts 관련 처리
 */
@Service
public class TtsService implements ITtsService{
	

	//callsVO 큐에 파일이름(경로 X)을 추가하고 TTS를 다운 로드
	public String downloadMP3(String msg, HttpSession session, String han) {
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
//				Member member = (Member)session.getAttribute("memberInfo");
//				CallsVO callsVO =CallsVoMap.userCallsVos.get(member.getMem_id());
//				placeHan2EnService.offer(callsVO, han, tempname);	                


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
	
	
	
	
}
