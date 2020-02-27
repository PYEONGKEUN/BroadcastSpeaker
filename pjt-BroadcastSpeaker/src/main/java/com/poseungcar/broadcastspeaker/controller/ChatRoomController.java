package com.poseungcar.broadcastspeaker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poseungcar.broadcastspeaker.VO.ChatRoomVO;
import com.poseungcar.broadcastspeaker.service.ChatRoomMgr;

@Controller
@RequestMapping("/chat")
public class ChatRoomController {
	
	@Autowired
	ChatRoomMgr chatRoomMgr;
	 
    // 채팅 리스트 화면
    @GetMapping("/room")
    public String rooms(Model model) {
        return "/chat/room";
    }
    // 모든 채팅방 목록 반환
    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoomVO> room() {
        return chatRoomMgr.findAllRoom();
    }
    // 채팅방 생성
    @PostMapping("/room")
    @ResponseBody
    public ChatRoomVO createRoom(@RequestParam String name) {
        return chatRoomMgr.createChatRoom(name);
    }
    // 채팅방 입장 화면
    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        return "/chat/roomdetail";
    }
    // 특정 채팅방 조회
    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoomVO roomInfo(@PathVariable String roomId) {
        return chatRoomMgr.findRoomById(roomId);
    }
	
}
