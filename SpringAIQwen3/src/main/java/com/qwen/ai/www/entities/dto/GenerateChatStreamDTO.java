package com.qwen.ai.www.entities.dto;

public class GenerateChatStreamDTO {
    private String role;
    private String userMessage;

    public GenerateChatStreamDTO() {
    }

    public GenerateChatStreamDTO(String role, String userMessage) {
        this.role = role;
        this.userMessage = userMessage;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
}
