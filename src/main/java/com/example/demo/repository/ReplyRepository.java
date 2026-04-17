package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    // 追加メソッド不要（Board削除時は cascade で削除）
}