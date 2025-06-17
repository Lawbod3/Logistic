package org.BodeLogistics.com.controllers;

import org.BodeLogistics.com.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/logistics/chat")
public class ChatMessageController {
    @Autowired
    ChatService chatService;

    @GetMapping("/test")
    public String test() {
        return "Api is working";
    }

}
