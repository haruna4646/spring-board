package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Reply;
import com.example.demo.service.ReplyService;

@Controller
@RequestMapping("/replies")
public class ReplyContoroller {

    private final ReplyService replyService;

    public ReplyContoroller(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public String create(Reply reply) {

        // 簡易バリデーション
        if (reply.getName() == null || reply.getName().isBlank()
                || reply.getMessage() == null || reply.getMessage().isBlank()) {
            return "redirect:/boards";
        }

        replyService.save(reply);
        return "redirect:/boards";
    }
}
