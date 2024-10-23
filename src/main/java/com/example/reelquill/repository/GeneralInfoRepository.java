package com.example.reelquill.repository;

import com.example.reelquill.model.GeneralInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeneralInfoRepository extends JpaRepository<GeneralInfo, Integer> {
    List<GeneralInfo> findAllByOrderByIdAsc();
}
