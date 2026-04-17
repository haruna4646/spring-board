package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardRepository boardRepository;

    // コンストラクタインジェクション
    public BoardController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /**
     * 一覧表示（新着順）
     * http://localhost:8080/boards
     */
    @GetMapping
    public String list(Model model) {
        List<Board> boards = boardRepository.findAllByOrderByCreatedAtDesc();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    /**
     * 検索
     * http://localhost:8080/boards/search?keyword=テスト
     */
    @GetMapping("/search")
    public String search(
            @RequestParam("keyword") String keyword,
            Model model) {

        List<Board> boards =
                boardRepository.findBySubjectContainingOrMessageContaining(keyword, keyword);

        model.addAttribute("boards", boards);
        model.addAttribute("keyword", keyword);
        return "board/list";
    }
}