package com.example.reelquill.service;

import com.example.reelquill.model.Friend;
import com.example.reelquill.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepo;

    public List<Friend> getAllFriends() {
        return friendRepo.findAll();
    }

    public Friend createFriend(Friend friend) {
        return friendRepo.save(friend);
    }

    public List<Friend> getAllFriendsByUserId(Integer userId) {
        return friendRepo.findByUserId(userId);
    }
}
