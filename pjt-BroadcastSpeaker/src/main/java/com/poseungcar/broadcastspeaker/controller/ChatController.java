package com.poseungcar.broadcastspeaker.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;

import com.poseungcar.broadcastspeaker.VO.ChatMessageVO;


@Controller
public class ChatController {
	
//	@Autowired
//	private final SimpMessageSendingOperations messagingTemplate;
//	 
//    @MessageMapping("/chat/message")
//    public void message(ChatMessageVO message) {
//        if (ChatMessageVO.MessageType.JOIN.equals(message.getType()))
//            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
//        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
//    }
}
