package com.example.reelquill.service;

import com.example.reelquill.model.Friend;
import com.example.reelquill.model.Quill;
import com.example.reelquill.repository.QuillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuillService {

    @Autowired
    private QuillRepository quillRepository;

    @Autowired
    private FriendService friendService;

    public Page<Quill> getAllQuills(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return quillRepository.findAll(pageable);
    }

    public Quill createQuill(Quill quill) {
        quill.setCreatedAt(LocalDateTime.now());
        return quillRepository.save(quill);
    }

    public Page<Quill> getFriendsQuills(Integer userId, int page, int size) {
        List<Friend> friends = friendService.getAllFriendsByUserId(userId);
        List<Integer> friendIds = friends.stream()
                .map(Friend::getFriendId)
                .collect(Collectors.toList());

        return quillRepository.findByUserIdIn(friendIds, PageRequest.of(page, size));
    }
}
