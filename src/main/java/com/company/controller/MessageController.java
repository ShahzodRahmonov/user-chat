package com.company.controller;

import com.company.dto.GetChatMessagesDTO;
import com.company.dto.MessageCreateDTO;
import com.company.dto.MessageDTO;
import com.company.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/add")
    public ResponseEntity<?> addMessage(@Valid @RequestBody MessageCreateDTO dto){
        Integer response = messageService.addMessage(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getChatAllMessages(@RequestBody GetChatMessagesDTO dto){
        List<MessageDTO> result = messageService.getChatAllMessages(dto);
        return ResponseEntity.ok(result);
    }

}
