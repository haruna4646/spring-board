package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDto;
import com.example.demo.dto.ReplyDto;
import com.example.demo.entity.Board;
import com.example.demo.form.BoardForm;
import com.example.demo.form.BoardSearchForm;
import com.example.demo.repository.BoardRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 一覧
    public List<BoardDto> findAll() {
        return boardRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(this::toDto)
                .toList();
    }

    // 検索（keyword）
    public List<BoardDto> search(BoardSearchForm form) {

        String keyword = emptyToNull(form.getKeyword());

        if (keyword == null) {
            return findAll();
        }

        return boardRepository
                .findBySubjectContainingOrMessageContainingOrNameContaining(keyword, keyword, keyword)
                .stream()
                .map(this::toDto)
                .toList();
    }

    private String emptyToNull(String value) {
        return (value == null || value.isBlank()) ? null : value;
    }

    // 投稿取得
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow();
        return toDto(board);
    }

    // 作成
    public void create(BoardForm form) {
        Board board = new Board();
        board.setName(form.getName());
        board.setEmail(form.getEmail());
        board.setSubject(form.getSubject());
        board.setMessage(form.getMessage());
        board.setDeleteKey(form.getDeleteKey());

        boardRepository.save(board);
    }

    // 削除
    public boolean delete(Long id, String deleteKey) {

        Board board = boardRepository.findById(id)
                .orElse(null);

        if (board == null) return false;

        String storedKey = board.getDeleteKey();

        if (storedKey != null) {
            if (deleteKey == null || !storedKey.equals(deleteKey)) {
                return false;
            }
        }

        boardRepository.delete(board);
        return true;
    }

    // DTO変換
    private BoardDto toDto(Board board) {

        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setName(board.getName());
        dto.setSubject(board.getSubject());
        dto.setMessage(board.getMessage());
        dto.setCreatedAt(board.getCreatedAt());

        dto.setReplies(
            board.getReplies().stream().map(r -> {
                ReplyDto rd = new ReplyDto();
                rd.setId(r.getId());
                rd.setBoardId(board.getId());
                rd.setName(r.getName());
                rd.setMessage(r.getMessage());
                rd.setCreatedAt(r.getCreatedAt());
                return rd;
            }).toList()
        );

        return dto;
    }
}