package com.example.demo.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByCreatedAtDesc();

    List<Board> findBySubjectContainingOrMessageContainingOrNameContaining(
            String subject,
            String message,
            String Name
    );

    Optional<Board> findById(Long id);

	Collection<BoardDto> findBySubjectContainingOrMessageContainingOrNameContaining1(String keyword, String keyword2,
			String keyword3);
}