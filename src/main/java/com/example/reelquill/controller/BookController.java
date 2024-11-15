package com.example.reelquill.controller;

import com.example.reelquill.dto.BookResponseDTO;
import com.example.reelquill.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService googleBooksService;

    @GetMapping("/getBooks")
    public List<BookResponseDTO> getBooks(@RequestParam String query) {
        return googleBooksService.searchBooks(query);
    }
}
