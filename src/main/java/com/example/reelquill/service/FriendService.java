package com.example.reelquill.service;

import com.example.reelquill.enums.NotificationStatus;
import com.example.reelquill.enums.NotificationType;
import com.example.reelquill.model.Friend;
import com.example.reelquill.model.Notification;
import com.example.reelquill.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {

    @Autowired
    private FriendRepository friendRepo;

    @Autowired
    private NotificationService notificationService;

    public List<Friend> getAllFriends() {
        return friendRepo.findAll();
    }

    public Friend createFriend(Friend friend) {
        Friend savedFriend = friendRepo.save(friend);

        Notification notification = new Notification();
        notification.setReceiverId(friend.getFriendId());
        notification.setSenderId(friend.getUserId());
        notification.setType(NotificationType.FRIEND_REQUEST.getCode());
        notification.setContent("You have a new friend request!");
        notification.setStatus(NotificationStatus.PENDING.getCode());
        notificationService.createNotification(notification);

        return savedFriend;
    }

    public List<Friend> getAllFriendsByUserId(Integer userId) {
        return friendRepo.findByUserId(userId);
    }

    public boolean areFriends(Integer userId, Integer friendId) {
        Friend friend = friendRepo.findByUserIdAndFriendId(userId, friendId);
        return friend != null;
    }
}
