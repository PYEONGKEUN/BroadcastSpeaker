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

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.service.ITtsService;
import com.poseungcar.broadcastspeaker.util.TimeLib;


/*
 * tts 관련 처리
 */

@Service
@PropertySource({"classpath:profiles/${spring.profiles.active}/application.properties"})
public class TtsService implements ITtsService{
	
	private static final Logger logger = LoggerFactory.getLogger(TtsService.class);
	
	@Value("${tts.location}")
	private String ttsLocation;
	@Value("${tts.uri_path}")
	private String ttsUriPath;

	//callsVO 큐에 파일이름(경로 X)을 추가하고 TTS를 다운 로드 
	public String downloadMP3(String msg, String id) {
		logger.info("-----downloadMP3 start-----");
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
			String speed = "-5";
			// post request
			String postParams = "speaker=nara&volume=0&speed="+speed+"&pitch=0&emotion=0&format=mp3&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			logger.info("-----postParams : "+postParams+"-----");
			int responseCode = con.getResponseCode();
			logger.info("-----responseCode : "+responseCode+"-----");
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				logger.info("-----Request Complete and start download tts-----");
				InputStream is = con.getInputStream();
				int read = 0;
				byte[] bytes = new byte[1024];
				// 시간으로 이름 생성
				String fileName = TimeLib.getCurrDateTimeName();
				result = "/"+id+"/"+fileName;				
				String filePath = ttsLocation+"/"+id;
				
				File dir = new File(filePath);
				
				if(!dir.exists()) {
					logger.info("dir path : " + dir.getPath());
					if(!dir.mkdirs()) {
						logger.info("폴더생성 성공");
					}else {
						logger.info("폴더생성 성공");
					}
					
				}
				
				File f = new File(filePath,fileName+".mp3");

				logger.info(" path : " + f.getPath());
				
				
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
			for(StackTraceElement element : e.getStackTrace()) {
				logger.error(element.toString());
				return null;
			}
		}
		logger.info("-----downloadMP3 end-----");
		return result;
	}
	
	
	
	
}
