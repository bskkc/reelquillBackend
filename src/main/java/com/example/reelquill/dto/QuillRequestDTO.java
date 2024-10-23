package com.example.reelquill.dto;

public class QuillRequestDTO {
    private Integer userId;
    private Integer generalInfoId;
    private String content;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGeneralInfoId() {
        return generalInfoId;
    }

    public void setGeneralInfoId(Integer generalInfoId) {
        this.generalInfoId = generalInfoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
