package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 投稿者名
    @Column(nullable = false, length = 50)
    private String name;

    // メールアドレス
    @Column(nullable = false)
    private String email;

    // 件名
    @Column(nullable = false, length = 100)
    private String subject;

    // 本文
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    // 削除キー（任意）
    @Column(name = "delete_key")
    private String deleteKey;

    // 投稿日時
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // 返信一覧（1投稿 : N返信）
    @OneToMany(
        mappedBy = "board",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<Reply> replies = new ArrayList<>();

    public Board() {}

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ===== getter / setter =====

    public Long getId() {
        return id;
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

    public List<Reply> getReplies() {
        return replies;
    }
}