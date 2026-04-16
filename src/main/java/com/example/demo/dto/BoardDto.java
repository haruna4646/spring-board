package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BoardDto {

    private Long id;

    // 入力・表示用（input.html と完全一致）
    private String name;
    private String email;
    private String subject;
    private String message;
    private String deleteKey;

    // 表示用
    private LocalDateTime createdAt;

    // 返信一覧（表示用）
    private List<ReplyDto> replies;

    // ===== getter / setter =====
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeleteKey() {
        return deleteKey;
    }
    public void setDeleteKey(String deleteKey) {
        this.deleteKey = deleteKey;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<ReplyDto> getReplies() {
        return replies;
    }
    public void setReplies(List<ReplyDto> replies) {
        this.replies = replies;
    }
}