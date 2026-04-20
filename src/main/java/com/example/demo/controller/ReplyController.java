package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.ReplyForm;
import com.example.demo.service.ReplyService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping
    public String create(
            @Valid ReplyForm form,
            BindingResult result) {

        if (result.hasErrors()) {
            // 本来はエラー保持して戻すが、まずは簡易対応
            return "redirect:/boards";
        }

        replyService.create(form);
        return "redirect:/boards";
    }
}