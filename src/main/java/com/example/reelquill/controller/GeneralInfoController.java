package com.example.reelquill.controller;

import com.example.reelquill.model.GeneralInfo;
import com.example.reelquill.service.GeneralInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/generalInfo")
public class GeneralInfoController {

    @Autowired
    private GeneralInfoService generalInfoService;

    @GetMapping
    public List<GeneralInfo> getAllGeneralInfo() {
        return generalInfoService.getAllGeneralInfo();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralInfo> getGeneralInfoById(@PathVariable int id) {
        return generalInfoService.getGeneralInfoById(id)
                .map(info -> ResponseEntity.ok(info))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeneralInfo(@PathVariable int id) {
        generalInfoService.deleteGeneralInfo(id);
        return ResponseEntity.noContent().build();
    }
}
