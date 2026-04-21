package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class BoardForm {

    @NotBlank(message = "名前は必須です")
    @Size(max = 15, message = "名前は15文字以内で入力してください")
    private String name;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレス形式で入力してください")
    private String email;

    @NotBlank(message = "件名は必須です")
    @Size(max = 100, message = "件名は100文字以内で入力してください")
    private String subject;

    @NotBlank(message = "本文は必須です")
    @Size(max = 1000, message = "本文は1000文字以内で入力してください")
    private String message;

    // 任意
    private String deleteKey;

    // ===== getter / setter =====
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSubject() { return subject; }
    public void setSubject(String subject) { this.subject = subject; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getDeleteKey() { return deleteKey; }
    public void setDeleteKey(String deleteKey) { this.deleteKey = deleteKey; }
}