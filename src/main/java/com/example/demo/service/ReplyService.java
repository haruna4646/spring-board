package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Board;
import com.example.demo.entity.Reply;
import com.example.demo.form.ReplyForm;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.ReplyRepository;

import jakarta.transaction.Transactional;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final BoardRepository boardRepository;

    public ReplyService(
            ReplyRepository replyRepository,
            BoardRepository boardRepository) {
        this.replyRepository = replyRepository;
        this.boardRepository = boardRepository;
    }

    // 返信投稿
    @Transactional
    public void create(ReplyForm form) {
        Board board = boardRepository.findById(form.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("投稿が存在しません"));

        Reply reply = new Reply();
        reply.setBoard(board);
        reply.setName(form.getName());
        reply.setMessage(form.getMessage());

        replyRepository.save(reply);
    }
}