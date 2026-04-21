package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.BoardForm;
import com.example.demo.form.BoardSearchForm;
import com.example.demo.form.ReplyForm;
import com.example.demo.service.BoardService;

import jakarta.validation.Valid;


@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // =====================
    // 一覧表示
    // =====================
    @GetMapping
    public String list(Model model) {

        model.addAttribute("boards", boardService.findAll());
        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("replyForm", new ReplyForm());
        model.addAttribute("searchForm", new BoardSearchForm());

        return "board/input";
    }

    // =====================
    // 検索（Formベース）
    // =====================
    @GetMapping("/search")
    public String search(BoardSearchForm form, Model model) {

        model.addAttribute("boards", boardService.search(form));
        model.addAttribute("searchForm", form);

        model.addAttribute("boardForm", new BoardForm());
        model.addAttribute("replyForm", new ReplyForm());

        return "board/input";
    }

    // =====================
    // 投稿確認
    // =====================
    @PostMapping("/confirm")
    public String confirm(BoardForm form, Model model) {

        model.addAttribute("boardForm", form);

        return "board/confirm";
    }

    // =====================
    // 投稿登録
    // =====================
    @PostMapping("/create")
    public String create(@Valid BoardForm form,
                         BindingResult result,
                         Model model) {

        if (result.hasErrors()) {

            model.addAttribute("boards", boardService.findAll());
            model.addAttribute("replyForm", new ReplyForm());
            model.addAttribute("searchForm", new BoardSearchForm());

            return "board/input";
        }

        boardService.create(form);
        return "redirect:/boards";
    }

    // =====================
    // 削除確認
    // =====================
    @PostMapping("/delete/confirm")
    public String deleteConfirm(@RequestParam Long id, Model model) {

        model.addAttribute("board", boardService.getBoard(id));

        return "board/delete";
    }

    // =====================
    // 削除実行
    // =====================
    @PostMapping("/delete/execute")
    public String deleteExecute(@RequestParam Long id,
                                @RequestParam(required = false) String deleteKey,
                                Model model) {

        boolean success = boardService.delete(id, deleteKey);

        if (!success) {

            model.addAttribute("board", boardService.getBoard(id));
            model.addAttribute("error", "削除キーが一致しません");

            return "board/delete";
        }

        return "redirect:/boards";
    }
}