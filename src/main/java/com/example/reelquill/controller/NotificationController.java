package com.example.reelquill.controller;

import com.example.reelquill.dto.NotificationResponseDTO;
import com.example.reelquill.model.Notification;
import com.example.reelquill.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification createdNotification = notificationService.createNotification(notification);
        return ResponseEntity.ok(createdNotification);
    }

    @GetMapping("/user/{receiverId}")
    public ResponseEntity<List<NotificationResponseDTO>> getNotificationsByReceiverId(@PathVariable Integer receiverId) {
        List<NotificationResponseDTO> responseDTOs = notificationService.getNotificationsByReceiverId(receiverId);
        return ResponseEntity.ok(responseDTOs);
    }
}
