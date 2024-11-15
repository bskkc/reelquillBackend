package com.example.reelquill.controller;

import com.example.reelquill.dto.MessageRequestDTO;
import com.example.reelquill.dto.MessageResponseDTO;
import com.example.reelquill.repository.UserRepository;
import com.example.reelquill.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class WebSocketController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserRepository userRepository;

    @MessageMapping("/send")
    @SendTo("/topic/messages")
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {
        messageService.sendMessage(request);

        MessageResponseDTO newMessage = new MessageResponseDTO();
        newMessage.setSenderId(request.getSenderId());
        newMessage.setReceiverId(request.getReceiverId());
        newMessage.setContent(request.getContent());
        newMessage.setTimestamp(LocalDateTime.now());
        newMessage.setSenderUsername(userRepository.findById(newMessage.getSenderId()).get().getUsername());
        newMessage.setReceiverUsername(userRepository.findById(newMessage.getReceiverId()).get().getUsername());
        newMessage.setIsRead(false);

        return newMessage;
    }
}
