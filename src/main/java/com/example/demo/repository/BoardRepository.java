package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    // 件名で部分一致検索
    List<Board> findBySubjectContaining(String keyword);

    // 投稿者名で部分一致検索
    List<Board> findByNameContaining(String keyword);

    // 件名＋本文のどちらかに含まれる検索
    List<Board> findBySubjectContainingOrMessageContaining(String subject, String message);

    // 新しい順で全件取得
    List<Board> findAllByOrderByCreatedAtDesc();
}