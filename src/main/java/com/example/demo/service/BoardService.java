package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // 一覧取得
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    // ID検索
    public Board findById(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    // 保存（投稿）
    public void save(Board board) {
        boardRepository.save(board);
    }

    // 削除
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    // タイトル検索
    public List<Board> search(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }
}