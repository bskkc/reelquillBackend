package com.example.reelquill.controller;

import com.example.reelquill.dto.QuillRequestDTO;
import com.example.reelquill.dto.QuillResponseDTO;
import com.example.reelquill.model.Friend;
import com.example.reelquill.model.Quill;
import com.example.reelquill.service.FriendService;
import com.example.reelquill.service.QuillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quills")
public class QuillController {

    @Autowired
    private QuillService quillService;

    @Autowired
    private FriendService friendService;

    @GetMapping
    public ResponseEntity<Page<QuillResponseDTO>> getAllQuills(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Page<QuillResponseDTO> responseDTOs = quillService.getAllQuills(page, size);
        return ResponseEntity.ok(responseDTOs);
    }

    @PostMapping
    public ResponseEntity<Page<QuillResponseDTO>> createQuill(@RequestBody QuillRequestDTO requestDTO) {
        quillService.createQuill(requestDTO);

        Page<QuillResponseDTO> responseDTOs = quillService.getAllQuills(0, 20);
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/friends/{userId}")
    public ResponseEntity<Page<QuillResponseDTO>> getFriendsQuills(@PathVariable Integer userId,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "20") int size) {
        Page<QuillResponseDTO> quills = quillService.getFriendsQuills(userId, page, size);
        return ResponseEntity.ok(quills);
    }

    @GetMapping("/generalInfo/{generalInfoId}")
    public ResponseEntity<List<QuillResponseDTO>> getQuillsByGeneralInfo(@PathVariable Integer generalInfoId) {
        List<QuillResponseDTO> quills = quillService.getQuillsByGeneralInfoId(generalInfoId);
        return ResponseEntity.ok(quills);
    }

    @GetMapping("/getUserQuills/{userId}")
    public ResponseEntity<List<QuillResponseDTO>> getQuillsByUserId(@PathVariable Integer userId) {
        List<QuillResponseDTO> userQuills = quillService.getQuillsByUserId(userId);
        return ResponseEntity.ok(userQuills);
    }

    private ResponseEntity<Page<QuillResponseDTO>> getPageResponseEntity(Page<Quill> quills) {
        Page<QuillResponseDTO> responseDTOs = quills.map(existingQuill -> {
            QuillResponseDTO dto = new QuillResponseDTO();
            dto.setId(existingQuill.getId());
            dto.setUserId(existingQuill.getUserId());
            dto.setUsername(existingQuill.getUsername());
            dto.setGeneralInfoId(existingQuill.getGeneralInfoId());
            dto.setQuill(existingQuill.getQuill());
            dto.setCreatedAt(existingQuill.getCreatedAt());
            return dto;
        });

        return ResponseEntity.ok(responseDTOs);
    }
}
