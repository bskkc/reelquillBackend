package com.example.reelquill.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MessageResponseDTO {
    private Long id;
    private int senderId;
    private int receiverId;
    private String senderUsername;
    private String receiverUsername;
    private String content;
    private LocalDateTime timestamp;
    private Boolean isRead;
    private List<MessageResponseDTO> groupedMessages;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public List<MessageResponseDTO> getGroupedMessages() {
        return groupedMessages;
    }

    public void setGroupedMessages(List<MessageResponseDTO> groupedMessages) {
        this.groupedMessages = groupedMessages;
    }
}
