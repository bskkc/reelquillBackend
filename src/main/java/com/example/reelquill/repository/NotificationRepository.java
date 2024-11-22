package com.example.reelquill.repository;

import com.example.reelquill.dto.NotificationResponseDTO;
import com.example.reelquill.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByReceiverId(Integer receiverId);
}
