package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 投稿者名
    private String name;

    // メール
    private String email;

    // 件名
    private String subject;

    // 本文
    @Column(columnDefinition = "TEXT")
    private String message;

    // 削除キー
    private String deleteKey;

    // 投稿日時
    private LocalDateTime createdAt;

    public Board() {}

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // ===== getter / setter =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getDeleteKey() { return deleteKey; }
    public void setDeleteKey(String deleteKey) { this.deleteKey = deleteKey; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
