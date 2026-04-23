package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

    List<Board> findBySubjectContainingOrMessageContainingOrNameContaining(
            String subject,
            String message,
            String name
    );

    Optional<Board> findById(Long id);
}