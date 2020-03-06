package com.poseungcar.broadcastspeaker.service;

import javax.servlet.http.HttpSession;

public interface ITtsService {

	public String downloadMP3(String msg, HttpSession session, String han);
}
