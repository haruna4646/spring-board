package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDto;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    public List<BoardDto> search(String keyword) {
        return boardRepository.findByTitleContaining(keyword)
                .stream()
                .map(this::toDto)
                .toList();
    }

    public void save(BoardDto dto) {
        Board board = toEntity(dto);
        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // ===== 変換処理 =====

    private BoardDto toDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setAuthor(board.getAuthor());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdatedAt(board.getUpdatedAt());
        return dto;
    }

    private Board toEntity(BoardDto dto) {
        Board board = new Board();
        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());
        board.setAuthor(dto.getAuthor());
        return board;
    }
}