package com.poseungcar.broadcastspeaker.status;

import java.util.HashMap;
import java.util.Map;

import com.poseungcar.broadcastspeaker.VO.CallsVO;

public class CallsVoMap {
	static public Map<String, CallsVO> userCallsVos = new  HashMap<String, CallsVO>() ;
	static public void setCallsVO(String id, CallsVO callsVo) {
		if(!userCallsVos.containsKey(id)) {
			userCallsVos.put(id, callsVo);
		}else {
			userCallsVos.replace(id, callsVo);
		}
		
	}
	
}
