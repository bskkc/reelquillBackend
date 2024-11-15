package com.example.reelquill.service;

import com.example.reelquill.dto.MessageRequestDTO;
import com.example.reelquill.dto.MessageResponseDTO;
import com.example.reelquill.model.Message;
import com.example.reelquill.model.User;
import com.example.reelquill.repository.MessageRepository;
import com.example.reelquill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public MessageResponseDTO sendMessage(MessageRequestDTO request) {
        Message message = new Message();
        message.setSenderId(request.getSenderId());
        message.setReceiverId(request.getReceiverId());
        message.setContent(request.getContent());
        message.setTimestamp(LocalDateTime.now());
        message.setRead(false);

        Message savedMessage = messageRepository.save(message);

        MessageResponseDTO responseDTO = new MessageResponseDTO();
        responseDTO.setId(savedMessage.getId());
        responseDTO.setSenderId(savedMessage.getSenderId());
        responseDTO.setReceiverId(savedMessage.getReceiverId());
        responseDTO.setContent(savedMessage.getContent());
        responseDTO.setTimestamp(savedMessage.getTimestamp());
        responseDTO.setIsRead(savedMessage.getRead());
        String senderUsername = userRepository.findById(savedMessage.getSenderId())
                .map(User::getUsername)
                .orElse("Unknown User");

        responseDTO.setSenderUsername(senderUsername);

        String receiverUsername = userRepository.findById(savedMessage.getReceiverId())
                .map(User::getUsername)
                .orElse("Unknown User");

        responseDTO.setReceiverUsername(receiverUsername);

        return responseDTO;
    }

    public List<MessageResponseDTO> getAllUserMessages(int userId) {
        List<Message> messages = messageRepository.findAllMessagesByUserId(userId);

        List<Message> filteredMessages = messages.stream()
                .filter(message -> message.getSenderId() == userId || message.getReceiverId() == userId)
                .collect(Collectors.toList());

        Map<Integer, List<MessageResponseDTO>> groupedMessages = filteredMessages.stream()
                .map(message -> {
                    MessageResponseDTO dto = new MessageResponseDTO();
                    dto.setId(message.getId());
                    dto.setContent(message.getContent());
                    dto.setSenderId(message.getSenderId());
                    dto.setReceiverId(message.getReceiverId());
                    String senderUsername = userRepository.findById(message.getSenderId())
                            .map(User::getUsername)
                            .orElse("Unknown User");

                    dto.setSenderUsername(senderUsername);

                    String receiverUsername = userRepository.findById(message.getReceiverId())
                            .map(User::getUsername)
                            .orElse("Unknown User");

                    dto.setReceiverUsername(receiverUsername);
                    dto.setTimestamp(message.getTimestamp());
                    return dto;
                })
                .collect(Collectors.groupingBy(dto ->
                        dto.getSenderId() == userId ? dto.getReceiverId() : dto.getSenderId()
                ));

        List<MessageResponseDTO> responseList = new ArrayList<>();
        for (Map.Entry<Integer, List<MessageResponseDTO>> entry : groupedMessages.entrySet()) {
            List<MessageResponseDTO> groupedList = entry.getValue();

            groupedList.sort(Comparator.comparing(MessageResponseDTO::getTimestamp));

            MessageResponseDTO latestMessage = groupedList.stream()
                    .max(Comparator.comparing(MessageResponseDTO::getTimestamp))
                    .orElse(null);

            MessageResponseDTO groupDto = new MessageResponseDTO();

            if (latestMessage != null) {
                groupDto.setId(UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE);
                groupDto.setReceiverId(entry.getKey());
                groupDto.setContent(latestMessage.getContent());
                groupDto.setSenderId(latestMessage.getSenderId());
                groupDto.setSenderUsername(latestMessage.getSenderUsername());
                groupDto.setReceiverUsername(latestMessage.getReceiverUsername());
                groupDto.setTimestamp(latestMessage.getTimestamp());
                groupDto.setIsRead(latestMessage.getIsRead());
            }

            groupDto.setGroupedMessages(groupedList);
            responseList.add(groupDto);
        }

        return responseList;
    }
}
