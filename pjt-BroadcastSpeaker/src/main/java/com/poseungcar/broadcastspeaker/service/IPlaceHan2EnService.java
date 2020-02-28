package com.poseungcar.broadcastspeaker.service;

import com.poseungcar.broadcastspeaker.VO.CallsVO;

public interface IPlaceHan2EnService {

	public void offer(CallsVO callsVo,String place, String mpUrl) throws Exception;
	public String poll(CallsVO callsVo,String place) throws Exception;
	public boolean isEmpty(CallsVO callsVo,String place) throws Exception;
}
