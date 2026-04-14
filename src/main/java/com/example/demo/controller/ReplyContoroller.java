package com.example.demo.controller;

import com.example.demo.entity.Reply;
import com.example.demo.service.ReplyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/replies")
public class ReplyContoroller {

    private final ReplyService replyService;

    public ReplyContoroller(ReplyService replyService) {
        this.replyService = replyService;
    }

    /**
     * 返信投稿処理
     * 画面：掲示板一覧（list.html）
     * 処理内容：
     *  ・hiddenで受け取った boardId に紐づけて返信を保存
     *  ・保存後は掲示板一覧へリダイレクト
     */
    @PostMapping
    public String create(Reply reply) {

        // バリデーション（最低限）
        if (reply.getAuthor() == null || reply.getAuthor().isBlank()
                || reply.getContent() == null || reply.getContent().isBlank()) {
            // 本来はエラー表示だが、簡易的に一覧へ戻す
            return "redirect:/boards";
        }

        replyService.save(reply);

        // PRGパターン（二重送信防止）
        return "redirect:/boards";
    }
}
