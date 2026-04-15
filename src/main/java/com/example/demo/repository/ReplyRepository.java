package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByBoardId(Long boardId);
}