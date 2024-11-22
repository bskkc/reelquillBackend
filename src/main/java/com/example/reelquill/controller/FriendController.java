package com.example.reelquill.controller;

import com.example.reelquill.dto.FriendRequestDTO;
import com.example.reelquill.dto.FriendResponseDTO;
import com.example.reelquill.model.Friend;
import com.example.reelquill.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @GetMapping
    public List<FriendResponseDTO> getAllFriends() {
        List<Friend> friends = friendService.getAllFriends();
        return friends.stream().map(friend -> {
            FriendResponseDTO dto = new FriendResponseDTO();
            dto.setId(friend.getId());
            dto.setUserId(friend.getUserId());
            dto.setFriendId(friend.getFriendId());
            return dto;
        }).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<FriendResponseDTO> createFriend(@RequestBody FriendRequestDTO requestDTO) {
        Friend friend = new Friend();
        friend.setUserId(requestDTO.getUserId());
        friend.setFriendId(requestDTO.getFriendId());

        Friend createdFriend = friendService.createFriend(friend);

        FriendResponseDTO responseDTO = new FriendResponseDTO();
        responseDTO.setId(createdFriend.getId());
        responseDTO.setUserId(createdFriend.getUserId());
        responseDTO.setFriendId(createdFriend.getFriendId());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkIfFriends(@RequestBody FriendRequestDTO friend) {
        boolean areFriends = friendService.areFriends(friend.getUserId(), friend.getFriendId());
        return ResponseEntity.ok(areFriends);
    }
}
