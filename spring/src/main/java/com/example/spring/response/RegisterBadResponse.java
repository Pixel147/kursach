package com.example.spring.response;

public class RegisterBadResponse {
    private int code;
    private String messageEmail;
    private String messageUsername;

    public RegisterBadResponse(int code, String messageEmail, String messageUsername) {
        this.code = code;
        this.messageEmail = messageEmail;
        this.messageUsername = messageUsername;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessageEmail() {
        return messageEmail;
    }

    public void setMessageEmail(String messageEmail) {
        this.messageEmail = messageEmail;
    }

    public String getMessageUsername() {
        return messageUsername;
    }

    public void setMessageUsername(String messageUsername) {
        this.messageUsername = messageUsername;
    }
}
