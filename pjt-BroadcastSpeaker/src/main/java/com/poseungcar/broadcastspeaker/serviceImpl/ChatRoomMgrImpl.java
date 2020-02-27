package com.poseungcar.broadcastspeaker.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poseungcar.broadcastspeaker.VO.ChatRoomVO;
import com.poseungcar.broadcastspeaker.service.ChatRoomMgr;

@Service
public class ChatRoomMgrImpl implements ChatRoomMgr{
	private Map<String, ChatRoomVO> chatRoomMap;


	public void init() {
		chatRoomMap = new LinkedHashMap<>();
	}

	public List<ChatRoomVO> findAllRoom() {
		// 채팅방 생성순서 최근 순으로 반환
		List chatRooms = new ArrayList<ChatRoomVO>();
		chatRooms.addAll(chatRoomMap.values());
		Collections.reverse(chatRooms);
		return chatRooms;
	}

	public ChatRoomVO findRoomById(String id) {
		return chatRoomMap.get(id);
	}

	public ChatRoomVO createChatRoom(String name) {
		ChatRoomVO chatRoomVO = new ChatRoomVO(name);
		chatRoomMap.put(chatRoomVO.getId(), chatRoomVO);
		return chatRoomVO;
	}
}
