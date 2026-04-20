package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReplyForm {

    @NotNull(message = "投稿IDは必須です")
    private Long boardId;

    @NotBlank(message = "名前は必須です")
    @Size(max = 50)
    private String name;

    @NotBlank(message = "本文は必須です")
    @Size(max = 1000)
    private String message;

    // getter / setter
    public Long getBoardId() { return boardId; }
    public void setBoardId(Long boardId) { this.boardId = boardId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}