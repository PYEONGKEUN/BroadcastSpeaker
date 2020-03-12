package com.poseungcar.broadcastspeaker.service;

public interface IClientService {
	public boolean callCarNumber(String id, String place, String number);
	public boolean callGrpMemName(String id, String place, String grpMemName);
}

