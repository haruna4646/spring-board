package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.entity.Board;
import com.example.demo.form.BoardForm;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 一覧取得（新着順）
    public List<BoardDto> findAll() {
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    // 検索
    public List<BoardDto> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }

        return boardRepository
                .findBySubjectContainingOrMessageContaining(keyword, keyword)
                .stream()
                .map(this::toDto)
                .toList();
    }

    // Entity → DTO
    private BoardDto toDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setName(board.getName());
        dto.setSubject(board.getSubject());
        dto.setMessage(board.getMessage());
        dto.setCreatedAt(board.getCreatedAt());
        

        List<ReplyDto> replyDtos = board.getReplies().stream()
                .map(r -> {
                    ReplyDto rd = new ReplyDto();
                    rd.setId(r.getId());
                    rd.setBoardId(board.getId());
                    rd.setName(r.getName());
                    rd.setMessage(r.getMessage());
                    rd.setCreatedAt(r.getCreatedAt());
                    return rd;
                })
                .toList();

        dto.setReplies(replyDtos);
        return dto;
    }
    
    public void create(BoardForm form) {
        Board board = new Board();
        board.setName(form.getName());
        board.setEmail(form.getEmail());
        board.setSubject(form.getSubject());
        board.setMessage(form.getMessage());
        board.setDeleteKey(form.getDeleteKey());

        boardRepository.save(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}