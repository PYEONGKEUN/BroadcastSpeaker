package com.poseungcar.broadcastspeaker.status;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.poseungcar.broadcastspeaker.VO.CallsVO;
import com.poseungcar.broadcastspeaker.service.ICallsVoMapService;

public class CallsVoMap {
	static public Map<String, CallsVO> userCallsVos = new  HashMap<String, CallsVO>() ;	
}
