package com.poseungcar.broadcastspeaker.VO;

import java.util.LinkedList;
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
	Queue<String> CustomerWaitingRoom= new LinkedList<>();
	public void offerCustomerWaitingRoom(String value) {
		CustomerWaitingRoom.offer(value);
	}
	public String pollCustomerWaitingRoom() {
		return CustomerWaitingRoom.poll();
	}
	public boolean isEmptyCustomerWaitingRoom() {
		return CustomerWaitingRoom.isEmpty();
	}
	//프론트
	Queue<String> front = new LinkedList<>();
	public void offerfront(String value) {
		front.offer(value);
	}
	public String pollfront() {
		return front.poll();
	}
	public boolean isEmptyfront() {
		return front.isEmpty();
	}
	//사무실
	Queue<String> office = new LinkedList<>();
	public void offeroffice(String value) {
		office.offer(value);
	}
	public String polloffice() {
		return office.poll();
	}
	public boolean isEmptyoffice() {
		return office.isEmpty();
	}
	//휴게실
	Queue<String> restArea = new LinkedList<>();
	public void offerrestArea(String value) {
		restArea.offer(value);
	}
	public String pollrestArea() {
		return restArea.poll();
	}
	public boolean isEmptyrestArea() {
		return restArea.isEmpty();
	}
	//정비실
	Queue<String> maintenanceRoom = new LinkedList<>();
	public void offermaintenanceRoom(String value) {
		maintenanceRoom.offer(value);
	}
	public String pollmaintenanceRoom() {
		return maintenanceRoom.poll();
	}
	public boolean isEmptymaintenanceRoom() {
		return maintenanceRoom.isEmpty();
	}
	//사장실
	Queue<String> presidentOffice = new LinkedList<>();
	public void offerpresidentOffice(String value) {
		presidentOffice.offer(value);
	}
	public String pollpresidentOffice() {
		return presidentOffice.poll();
	}
	public boolean isEmptypresidentOffice() {
		return presidentOffice.isEmpty();
	}
	//창구
	Queue<String> window = new LinkedList<>();
	public void offerwindow(String value) {
		window.offer(value);
	}
	public String pollwindow() {
		return window.poll();
	}
	public boolean isEmptywindow() {
		return window.isEmpty();
	}



	public String toString() {
		return "CallsVO [userId=" + userId + ", CustomerWaitingRoom=" + CustomerWaitingRoom + ", front=" + front
				+ ", office=" + office + ", restArea=" + restArea + ", maintenanceRoom=" + maintenanceRoom
				+ ", presidentOffice=" + presidentOffice + ", window=" + window + "]";
	}
	
	
	
}
