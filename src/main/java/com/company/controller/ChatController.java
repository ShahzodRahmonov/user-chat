package com.company.controller;

import com.company.dto.ChatCreateDTO;
import com.company.dto.ChatDTO;
import com.company.dto.GetUserChatsDTO;
import com.company.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/add")
    public ResponseEntity<?> addChat(@Valid @RequestBody ChatCreateDTO dto){
        Integer response = chatService.addChat(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getUserAllChats(@RequestBody GetUserChatsDTO dto){
        List<ChatDTO> result = chatService.getUserAllChats(dto);
        return ResponseEntity.ok(result);
    }
}
