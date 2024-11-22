package com.example.reelquill.dto;

import com.example.reelquill.model.User;

public class LoginResponseDTO {
    private String token;
    private String message;
    private User user;

    public LoginResponseDTO(String token, String message, User user) {
        this.token = token;
        this.message = message;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
    public User getUser() {
        return user;
    }
}
