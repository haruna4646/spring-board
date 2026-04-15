package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.BoardDto;
import com.example.demo.service.BoardService;
import com.example.demo.service.ReplyService;

@Controller
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;
    private final ReplyService replyService;

    public BoardController(BoardService boardService, ReplyService replyService) {
        this.boardService = boardService;
        this.replyService = replyService;
    }

    // 一覧＋検索
    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {

    	List<BoardDto> boards =
    	        (keyword == null || keyword.isEmpty())
    	                ? boardService.findAll()
    	                : boardService.search(keyword);

        model.addAttribute("boards", boards);
        return "board/list";
    }

    // 投稿
    @PostMapping
    public String create(BoardDto boardDto) {
        boardService.save(boardDto);
        return "redirect:/boards";
    }

    // 削除
    @PostMapping("/delete")
    public String delete(@RequestParam Long id) {
        boardService.delete(id);
        return "redirect:/boards";
    }
}