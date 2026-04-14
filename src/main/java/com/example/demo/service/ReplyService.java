package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Reply;
import com.example.demo.repository.ReplyRepository;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    // 特定の投稿に紐づく返信一覧を取得
    public List<Reply> findByBoardId(Long boardId) {
        return replyRepository.findByBoardId(boardId);
    }

    // 返信を保存
    public void save(Reply reply) {
        replyRepository.save(reply);
    }
}
