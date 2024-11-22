package com.example.reelquill.repository;

import com.example.reelquill.model.Quill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuillRepository extends JpaRepository<Quill, Integer> {
    Page<Quill> findByUserIdIn(List<Integer> userIds, Pageable pageable);
    List<Quill> findByGeneralInfoId(Integer generalInfoId);
    List<Quill> findByUserId(Integer userId);
}
