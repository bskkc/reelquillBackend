package com.example.reelquill.controller;

import com.example.reelquill.dto.SongResponseDTO;
import com.example.reelquill.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/searchSongsWithArtist")
    public List<SongResponseDTO> searchSongsWithArtist(@RequestParam String query) {
        return songService.searchSongsWithArtist(query);
    }

    @GetMapping("/searchSongsWithName")
    public List<SongResponseDTO> searchSongsWithName(@RequestParam String query) {
        return songService.searchSongsWithName(query);
    }
}
