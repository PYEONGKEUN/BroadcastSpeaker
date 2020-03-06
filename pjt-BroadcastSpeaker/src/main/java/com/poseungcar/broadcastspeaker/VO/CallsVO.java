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
	public void offerCustomerWaitingRoom(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		CustomerWaitingRoom.offer(value);
	}
	public Map<String, String> pollCustomerWaitingRoom() {
		return CustomerWaitingRoom.poll();
	}
	public boolean isEmptyCustomerWaitingRoom() {
		return CustomerWaitingRoom.isEmpty();
	}
	//프론트
	Queue<Map<String, String>> front = new LinkedList<Map<String, String>>();
	public void offerFront(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		front.offer(value);
	}
	public Map<String, String> pollFront() {
		return front.poll();
	}
	public boolean isEmptyFront() {
		return front.isEmpty();
	}
	//사무실
	Queue<Map<String, String>> office = new LinkedList<Map<String, String>>();
	public void offerOffice(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		office.offer(value);
	}
	public Map<String, String> pollOffice() {
		return office.poll();
	}
	public boolean isEmptyOffice() {
		return office.isEmpty();
	}
	//휴게실
	Queue<Map<String, String>> restArea = new LinkedList<Map<String, String>>();
	public void offerRestArea(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		restArea.offer(value);
	}
	public Map<String, String> pollRestArea() {
		return restArea.poll();
	}
	public boolean isEmptyRestArea() {
		return restArea.isEmpty();
	}
	//정비실
	Queue<Map<String, String>> maintenanceRoom = new LinkedList<Map<String, String>>();
	public void offerMaintenanceRoom(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		maintenanceRoom.offer(value);
	}
	public Map<String, String> pollMaintenanceRoom() {
		return maintenanceRoom.poll();
	}
	public boolean isEmptyMaintenanceRoom() {
		return maintenanceRoom.isEmpty();
	}
	//사장실
	Queue<Map<String, String>> presidentOffice = new LinkedList<Map<String, String>>();
	public void offerPresidentOffice(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		presidentOffice.offer(value);
	}
	public Map<String, String> pollPresidentOffice() {
		return presidentOffice.poll();
	}
	public boolean isEmptyPresidentOffice() {
		return presidentOffice.isEmpty();
	}
	//창구
	Queue<Map<String, String>> window = new LinkedList<Map<String, String>>();
	public void offerWindow(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		window.offer(value);
	}
	public Map<String, String> pollWindow() {
		return window.poll();
	}
	public boolean isEmptyWindow() {
		return window.isEmpty();
	}
	
	//직원 휴게실
	Queue<Map<String, String>> seniorCommonRoom = new LinkedList<Map<String, String>>();
	public void offerSeniorCommonRoom(String number, String fileName) {
		Map<String, String> value = new HashMap<String, String>();
		value.put("number", number);
		value.put("fileName", fileName);
		window.offer(value);
	}
	public Map<String, String> pollSeniorCommonRoom() {
		return window.poll();
	}
	public boolean isEmptySeniorCommonRoom() {
		return window.isEmpty();
	}
	
	@Override
	public String toString() {
		return "CallsVO [userId=" + userId + ", CustomerWaitingRoom=" + CustomerWaitingRoom + ", front=" + front
				+ ", office=" + office + ", restArea=" + restArea + ", maintenanceRoom=" + maintenanceRoom
				+ ", presidentOffice=" + presidentOffice + ", window=" + window + ", seniorCommonRoom="
				+ seniorCommonRoom + "]";
	}	
	
}

