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

    // 一覧取得
    public List<BoardDto> findAll() {
        return boardRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }

    // 保存
    public void save(BoardDto dto) {
        Board board = toEntity(dto);
        boardRepository.save(board);
    }

    // 削除
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // ===== Entity → DTO =====
    private BoardDto toDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setName(board.getName());
        dto.setEmail(board.getEmail());
        dto.setSubject(board.getSubject());
        dto.setMessage(board.getMessage());
        dto.setDeleteKey(board.getDeleteKey());
        dto.setCreatedAt(board.getCreatedAt());
        return dto;
    }

    // ===== DTO → Entity =====
    private Board toEntity(BoardDto dto) {
        Board board = new Board();
        board.setName(dto.getName());
        board.setEmail(dto.getEmail());
        board.setSubject(dto.getSubject());
        board.setMessage(dto.getMessage());
        board.setDeleteKey(dto.getDeleteKey());
        return board;
    }
}