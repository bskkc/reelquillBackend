package com.example.reelquill.service;

import com.example.reelquill.dto.MessageResponseDTO;
import com.example.reelquill.dto.NotificationResponseDTO;
import com.example.reelquill.dto.QuillResponseDTO;
import com.example.reelquill.model.Message;
import com.example.reelquill.model.Notification;
import com.example.reelquill.model.User;
import com.example.reelquill.repository.NotificationRepository;
import com.example.reelquill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<NotificationResponseDTO> getNotificationsByReceiverId(Integer receiverId) {
        List<Notification> notifications = notificationRepository.findByReceiverId(receiverId);

        List<NotificationResponseDTO> responseDTOs = notifications.stream()
                .map(notification -> {
                    NotificationResponseDTO responseDTO = new NotificationResponseDTO();

                    responseDTO.setId(notification.getId());
                    responseDTO.setReceiverId(notification.getReceiverId());
                    responseDTO.setSenderId(notification.getSenderId());
                    String senderUsername = userRepository.findById(notification.getSenderId())
                            .map(User::getUsername)
                            .orElse("Unknown User");

                    responseDTO.setSenderUsername(senderUsername);
                    responseDTO.setType(notification.getType());
                    responseDTO.setStatus(notification.getStatus());
                    responseDTO.setContent(notification.getContent());
                    responseDTO.setData(notification.getData());
                    responseDTO.setCreatedAt(notification.getCreatedAt());
                    responseDTO.setUpdatedAt(notification.getUpdatedAt());

                    return responseDTO;
                })
                .collect(Collectors.toList());

        responseDTOs.sort(Comparator.comparing(NotificationResponseDTO::getCreatedAt).reversed());

        return responseDTOs;
    }
}
