package com.poseungcar.broadcastspeaker.VO;

import java.util.UUID;

public class ChatRoomVO {
	String id = UUID.randomUUID().toString();
	String name = "";
	public ChatRoomVO(String name) {
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "ChatRoom [id=" + id + ", name=" + name + "]";
	}
	
}
