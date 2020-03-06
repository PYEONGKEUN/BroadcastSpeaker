package com.poseungcar.broadcastspeaker.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.IPlaceHan2EnService;
import com.poseungcar.broadcastspeaker.status.CallsVoMap;



/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
@Autowired
IPlaceHan2EnService placeHan2EnService;
	

	@RequestMapping(value = "/ip", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody String getIp (@RequestBody Map<String, Object> map, HttpServletRequest request)	{
		
        String remoteIp = request.getHeader("X-FORWARDED-FOR");
        String remoteHost = request.getRemoteHost().toString();
        int remotePort = request.getRemotePort();
        if (remoteIp == null)
        	remoteIp = request.getRemoteAddr();
        String result = "client addr : "+ remoteIp+":"+remotePort+"   remote Host : "+remoteHost;
		return result;
	}	
	
	
	
	@PostMapping(value = "/request2", consumes = "application/json", produces = "application/json")
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
	
	@PostMapping(value = "/download", consumes = "application/json", produces = "application/json")
	public ResponseEntity<InputStreamResource> stream ( 
			@RequestAttribute(name = "filename")String fileName
			) throws Exception{				

		File file = new File("/opt/clovatest/"+fileName + ".mp3");
		
		
		
		// 스트리밍 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-disposition", "attachment; filename="+fileName+".mp3");
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.body(new InputStreamResource(new FileInputStream(file)));
	}

}

