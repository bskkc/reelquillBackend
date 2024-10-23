package com.example.reelquill.service;

import com.example.reelquill.model.GeneralInfo;
import com.example.reelquill.repository.GeneralInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneralInfoService {

    @Autowired
    private GeneralInfoRepository generalInfoRepo;

    public List<GeneralInfo> getAllGeneralInfo() {
        return generalInfoRepo.findAllByOrderByIdAsc();
    }

    public Optional<GeneralInfo> getGeneralInfoById(int id) {
        return generalInfoRepo.findById(id);
    }
    public void deleteGeneralInfo(int id) {
        generalInfoRepo.deleteById(id);
    }
}
