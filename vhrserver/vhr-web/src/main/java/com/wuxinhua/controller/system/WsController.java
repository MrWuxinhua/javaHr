package com.wuxinhua.controller.system;

import com.wuxinhua.model.ChatMsg;
import com.wuxinhua.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class WsController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/ws/chat")
    public void handMsg(Authentication authentication, ChatMsg chatMsg) {
        Hr hr = (Hr) authentication.getPrincipal();

        chatMsg.setFrom(hr.getUsername());  //英文名
        chatMsg.setFromZH(hr.getName()); //中文名
        chatMsg.setDate(new Date());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
