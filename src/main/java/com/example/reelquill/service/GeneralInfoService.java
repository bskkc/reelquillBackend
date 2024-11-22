package com.example.reelquill.service;

import com.example.reelquill.dto.GeneralInfoResponseDTO;
import com.example.reelquill.dto.RateGeneralInfoRequestDTO;
import com.example.reelquill.model.GeneralInfo;
import com.example.reelquill.model.Quill;
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

    public GeneralInfo rateGeneralInfo(RateGeneralInfoRequestDTO rateGeneralInfoRequestDTO) {
        double newRate = rateGeneralInfoRequestDTO.getRate();
        GeneralInfo generalInfo = generalInfoRepo.findById(rateGeneralInfoRequestDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Film bulunamadı"));

        double currentRating = generalInfo.getRating();
        double newRating = (currentRating + newRate) / 2.0;
        generalInfo.setRating(newRating);
        generalInfoRepo.save(generalInfo);

        return generalInfo;
    }
}
