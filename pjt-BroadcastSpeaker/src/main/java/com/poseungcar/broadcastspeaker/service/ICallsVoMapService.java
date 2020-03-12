package com.poseungcar.broadcastspeaker.service;

import java.util.Map;

public interface ICallsVoMapService {

	public void offer(String id, String place, String msg, String type, String fileName) throws Exception;
	public Map<String, String> poll(String id,String place) throws Exception;
	public boolean isEmpty(String id,String place) throws Exception;
}
