package com.example.reelquill.controller;

import com.example.reelquill.dto.MessageRequestDTO;
import com.example.reelquill.dto.MessageResponseDTO;
import com.example.reelquill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/history/{userId}")
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages(@PathVariable int userId) {
        List<MessageResponseDTO> messages = messageService.getAllUserMessages(userId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponseDTO> sendMessage(@RequestBody MessageRequestDTO request) {
        MessageResponseDTO message = messageService.sendMessage(request);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

