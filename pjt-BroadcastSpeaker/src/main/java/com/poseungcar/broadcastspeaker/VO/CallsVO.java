package com.poseungcar.broadcastspeaker.VO;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CallsVO {
	String userId = "";
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	//고객 대기실
	Queue<Map<String, String>> CustomerWaitingRoom= new LinkedList<Map<String, String>>();
	public void offerCustomerWaitingRoom(String msg, String type, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("msg", msg);value.put("type", type);value.put("type", type);
		value.put("fileName", fileName);
		CustomerWaitingRoom.offer(value);
	}
	public Map<String, String> pollCustomerWaitingRoom() {
		return CustomerWaitingRoom.poll();
	}
	public boolean isEmptyCustomerWaitingRoom() {
		return CustomerWaitingRoom.isEmpty();
	}

	//사무실
	Queue<Map<String, String>> office = new LinkedList<Map<String, String>>();
	public void offerOffice(String msg, String type, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("msg", msg);value.put("type", type);
		value.put("fileName", fileName);
		office.offer(value);
	}
	public Map<String, String> pollOffice() {
		return office.poll();
	}
	public boolean isEmptyOffice() {
		return office.isEmpty();
	}

	
	//직원 휴게실
	Queue<Map<String, String>> seniorCommonRoom = new LinkedList<Map<String, String>>();
	public void offerSeniorCommonRoom(String msg, String type, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("msg", msg);value.put("type", type);
		value.put("fileName", fileName);
		seniorCommonRoom.offer(value);
	}
	public Map<String, String> pollSeniorCommonRoom() {
		return seniorCommonRoom.poll();
	}
	public boolean isEmptySeniorCommonRoom() {
		return seniorCommonRoom.isEmpty();
	}
	@Override
	public String toString() {
		return "CallsVO [userId=" + userId + ", CustomerWaitingRoom=" + CustomerWaitingRoom + ", office=" + office
				+ ", seniorCommonRoom=" + seniorCommonRoom + "]";
	}

	
	
}

