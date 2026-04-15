package com.example.demo.dto;

import java.time.LocalDateTime;

public class BoardDto {

    private Long id;

    // 画面入力・表示用
    private String title;
    private String content;
    private String author;

    // 表示用（登録・更新日時）
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // コンストラクタ
    public BoardDto() {}

    // getter / setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}