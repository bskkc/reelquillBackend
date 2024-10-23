package com.example.reelquill.repository;

import com.example.reelquill.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Integer> {
    List<Friend> findByUserId(Integer userId);
}
