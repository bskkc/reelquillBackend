package com.example.reelquill.repository;

import com.example.reelquill.model.GeneralInfo;
import com.example.reelquill.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findAllByReceiverId(Long receiverId);

    @Query("SELECT m FROM Message m WHERE m.senderId = :userId OR m.receiverId = :userId")
    List<Message> findAllMessagesByUserId(@Param("userId") int userId);
}

