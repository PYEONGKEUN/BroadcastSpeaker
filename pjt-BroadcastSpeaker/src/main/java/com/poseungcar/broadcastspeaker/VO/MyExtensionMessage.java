package com.poseungcar.broadcastspeaker.VO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MyExtensionMessage {
	public String version = "1.0";
	public Map<String,Object> sessionAttributes = new HashMap<String,Object>();
	public MyResponse response = null;
	public MyExtensionMessage(String INTENT_NAME, boolean session, ArrayList<HashMap<String,Object>> myResponseValue){
		if(!session) {
			sessionAttributes.put("intent", INTENT_NAME);
		}
		MyResponse response = new MyResponse();
		response.shouldEndSession = session;
		response.outputSpeech.put("type", "SpeechList");
		response.outputSpeech.put("values", myResponseValue);
		this.response = response;
	}
	public MyExtensionMessage(String INTENT_NAME, String message, boolean session, String type)
	{ 
		// MyResponseValue 객체 
		Map<String, String> myResponseValue = new HashMap<String, String>();
		myResponseValue.put("type", type);
		myResponseValue.put("lang", "ko");
		myResponseValue.put("value", message);
		if(!session){
			sessionAttributes.put("intent", INTENT_NAME);
		} 
		MyResponse response = new MyResponse();
		response.shouldEndSession = session;
		response.outputSpeech.put("type", "SimpleSpeech");
		response.outputSpeech.put("values", myResponseValue);
		this.response = response;
	}
	public static class MyResponse {
		public Map<String, Object> outputSpeech = new HashMap<String, Object>();
		public Map<String, HashMap<String, Object>> card = new HashMap<String, HashMap<String, Object>>();
		public ArrayList<String>  directives = new ArrayList<String>(); 
		public boolean shouldEndSession = false; 
	} 
}
