package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Reply;
import com.example.demo.service.ReplyService;

@Controller
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public String create(Reply reply) {

        if (reply.getAuthor() == null || reply.getAuthor().isBlank()
                || reply.getContent() == null || reply.getContent().isBlank()) {
            return "redirect:/boards";
        }

        replyService.save(reply);

        return "redirect:/boards";
    }
}
