package com.example.reelquill.service;

import com.example.reelquill.dto.BookResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public BookService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public List<BookResponseDTO> searchBooks(String query) {
        String url = "https://www.googleapis.com/books/v1/volumes?q=" + query;
        String response = restTemplate.getForObject(url, String.class);

        List<BookResponseDTO> bookDTOList = new ArrayList<>();

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root.path("items");

            for (JsonNode item : items) {
                JsonNode volumeInfo = item.path("volumeInfo");

                BookResponseDTO bookDTO = new BookResponseDTO();
                bookDTO.setTitle(volumeInfo.path("title").asText());
                bookDTO.setAuthors(objectMapper.convertValue(volumeInfo.path("authors"), List.class));
                bookDTO.setDescription(volumeInfo.path("description").asText());
                bookDTO.setThumbnail(volumeInfo.path("imageLinks").path("thumbnail").asText());

                bookDTOList.add(bookDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookDTOList;
    }
}
