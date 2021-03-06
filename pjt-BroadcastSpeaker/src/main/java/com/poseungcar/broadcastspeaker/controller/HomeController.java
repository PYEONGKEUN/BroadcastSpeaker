package com.poseungcar.broadcastspeaker.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;



/**
 * Handles requests for the application home page.
 */
@RestController
public class HomeController {
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
ICallsVoMapService callsVoMapService;
	

	@RequestMapping(value = "/ip", method= RequestMethod.POST, produces = "application/json" )
	@ResponseBody String getIp (@RequestBody Map<String, Object> map, HttpServletRequest request)	{
		
        String remoteIp = request.getHeader("X-FORWARDED-FOR");
        String remoteHost = request.getRemoteHost().toString();
        int remotePort = request.getRemotePort();
        if (remoteIp == null)
        	remoteIp = request.getRemoteAddr();
        String result = "client addr : "+ remoteIp+":"+remotePort+"   remote Host : "+remoteHost;
        logger.info(result);
		return result;
	}	
	
	
	


	
	    
}

