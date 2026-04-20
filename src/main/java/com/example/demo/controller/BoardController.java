package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    // 一覧
    @GetMapping
    public String list(Model model) {
        model.addAttribute("boards", boardService.findAll());
        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("replyForm", new ReplyForm());
        model.addAttribute("searchForm", new BoardSearchForm());
        return "board/input";
    }

    // 検索
    @GetMapping("/search")
    public String search(BoardSearchForm form, Model model) {
        model.addAttribute("boards", boardService.search(form.getKeyword()));
        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("replyForm", new ReplyForm());
        model.addAttribute("searchForm", form);
        return "board/input";
    }

    // confirm画面
    @PostMapping("/confirm")
    public String confirm(BoardForm form, Model model) {
        model.addAttribute("boardForm", form);
        return "board/confirm";
    }

    // 投稿処理
    @PostMapping("/create")
    public String create(BoardForm form) {
        boardService.create(form);
        return "redirect:/boards";
    }

    // 削除（GET簡易版）
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }
}