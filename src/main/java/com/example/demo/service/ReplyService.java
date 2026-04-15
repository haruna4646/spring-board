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

    public List<Reply> findByBoardId(Long boardId) {
        return replyRepository.findByBoardId(boardId);
    }

    public void save(Reply reply) {
        replyRepository.save(reply);
    }
}