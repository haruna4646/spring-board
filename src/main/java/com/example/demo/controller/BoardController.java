package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.BoardForm;
import com.example.demo.form.BoardSearchForm;
import com.example.demo.form.ReplyForm;
import com.example.demo.service.BoardService;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 一覧表示（新着順）
    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        model.addAttribute("replyForm", new ReplyForm());
        model.addAttribute("boardForm" , new BoardForm());
        model.addAttribute("searchForm" , new BoardSearchForm());
        return "board/input";
    }

    // 検索
    @GetMapping("/search")
    public String search(BoardSearchForm form, Model model) {
        model.addAttribute("boards", boardService.search(form));
        model.addAttribute("replyForm", new ReplyForm());
        model.addAttribute("searchForm", form);
        model.addAttribute("boardForm" , new BoardForm());
        return "board/input";
    }
}