package com.example.reelquill.service;

import com.example.reelquill.dto.QuillRequestDTO;
import com.example.reelquill.dto.QuillResponseDTO;
import com.example.reelquill.model.Friend;
import com.example.reelquill.model.Message;
import com.example.reelquill.model.Quill;
import com.example.reelquill.model.User;
import com.example.reelquill.repository.QuillRepository;
import com.example.reelquill.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuillService {

    @Autowired
    private QuillRepository quillRepository;

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserRepository userRepository;

    public Page<QuillResponseDTO> getAllQuills(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Quill> quills = quillRepository.findAll(pageable);

        Page<QuillResponseDTO> responseDTOs = quills.map(quill -> {
            QuillResponseDTO responseDTO = new QuillResponseDTO();
            responseDTO.setId(quill.getId());
            responseDTO.setQuill(quill.getQuill());
            responseDTO.setCreatedAt(quill.getCreatedAt());
            String username = userRepository.findById(quill.getUserId())
                    .map(User::getUsername)
                    .orElse("Unknown User");

            responseDTO.setUsername(username);
            responseDTO.setUserId(quill.getUserId());
            responseDTO.setGeneralInfoId(quill.getGeneralInfoId());

            return responseDTO;
        });

        return responseDTOs;
    }

    public Quill createQuill(QuillRequestDTO quillRequestDTO) {
        Quill newQuill = new Quill();
        newQuill.setQuill(quillRequestDTO.getQuill());
        newQuill.setGeneralInfoId(quillRequestDTO.getGeneralInfoId());
        newQuill.setUserId(quillRequestDTO.getUserId());

        return quillRepository.save(newQuill);
    }

    public Page<QuillResponseDTO> getFriendsQuills(Integer userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        List<Friend> friends = friendService.getAllFriendsByUserId(userId);

        List<Integer> friendIds = friends.stream()
                .flatMap(friend -> Stream.of(friend.getFriendId(), friend.getUserId()))  // Arkadaşların ve kendi ID'lerinin her ikisini de ekliyoruz
                .distinct()  // Duplicated IDs'leri engelliyoruz
                .collect(Collectors.toList());

        Page<Quill> quills = quillRepository.findByUserIdIn(friendIds, pageable);

        Page<QuillResponseDTO> responseDTOs = quills.map(quill -> {
            QuillResponseDTO responseDTO = new QuillResponseDTO();
            responseDTO.setId(quill.getId());
            responseDTO.setQuill(quill.getQuill());
            responseDTO.setCreatedAt(quill.getCreatedAt());

            String username = userRepository.findById(quill.getUserId())
                    .map(User::getUsername)
                    .orElse("Unknown User");

            responseDTO.setUsername(username);
            responseDTO.setUserId(quill.getUserId());
            responseDTO.setGeneralInfoId(quill.getGeneralInfoId());

            return responseDTO;
        });

        return responseDTOs;
    }

    public List<QuillResponseDTO> getQuillsByGeneralInfoId(Integer generalInfoId) {
        List<Quill> quills = quillRepository.findByGeneralInfoId(generalInfoId);

        List<QuillResponseDTO> responseDTOs = quills.stream()
                .map(quill -> {
                    QuillResponseDTO responseDTO = new QuillResponseDTO();
                    responseDTO.setId(quill.getId());
                    responseDTO.setQuill(quill.getQuill());
                    responseDTO.setCreatedAt(quill.getCreatedAt());
                    responseDTO.setUsername(userRepository.findById(quill.getUserId()).get().getUsername());
                    return responseDTO;
                })
                .collect(Collectors.toList());

        return responseDTOs;
    }

    public List<QuillResponseDTO> getQuillsByUserId(Integer userId) {
        List<Quill> quills = quillRepository.findByUserId(userId);

        List<QuillResponseDTO> responseDTOs = quills.stream()
                .map(quill -> {
                    QuillResponseDTO responseDTO = new QuillResponseDTO();
                    responseDTO.setId(quill.getId());
                    responseDTO.setQuill(quill.getQuill());
                    responseDTO.setCreatedAt(quill.getCreatedAt());
                    responseDTO.setUsername(userRepository.findById(quill.getUserId()).get().getUsername());
                    return responseDTO;
                })
                .collect(Collectors.toList());

        return responseDTOs;
    }

}
