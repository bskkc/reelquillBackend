package com.example.reelquill.dto;

import java.time.LocalDateTime;

public class QuillResponseDTO {
    private Integer id;
    private Integer userId;
    private String username;
    private Integer generalInfoId;
    private String quill;
    private LocalDateTime createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGeneralInfoId() {
        return generalInfoId;
    }

    public void setGeneralInfoId(Integer generalInfoId) {
        this.generalInfoId = generalInfoId;
    }

    public String getQuill() {
        return quill;
    }

    public void setQuill(String content) {
        this.quill = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
