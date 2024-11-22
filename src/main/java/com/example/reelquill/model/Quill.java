package com.example.reelquill.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "quills")
public class Quill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Size(max = 50)
    @Column(name = "username")
    private String username;

    @Column(name = "general_info_id")
    private Integer generalInfoId;

    @Column(name = "quill", length = 500)
    private String quill;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

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

    public Integer getGeneralInfoId() {
        return generalInfoId;
    }

    public void setGeneralInfoId(Integer generalInfoId) {
        this.generalInfoId = generalInfoId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public @Size(max = 50) String getUsername() {
        return username;
    }

    public void setUsername(@Size(max = 50) String username) {
        this.username = username;
    }

    public String getQuill() {
        return quill;
    }

    public void setQuill(String quill) {
        this.quill = quill;
    }
}
