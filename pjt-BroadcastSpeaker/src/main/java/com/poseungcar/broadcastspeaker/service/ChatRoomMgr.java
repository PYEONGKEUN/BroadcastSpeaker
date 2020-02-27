package com.poseungcar.broadcastspeaker.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.poseungcar.broadcastspeaker.VO.ChatRoomVO;

public interface ChatRoomMgr {
    public void init();
    public List<ChatRoomVO> findAllRoom(); 
    public ChatRoomVO findRoomById(String id);
    public ChatRoomVO createChatRoom(String name);
}
