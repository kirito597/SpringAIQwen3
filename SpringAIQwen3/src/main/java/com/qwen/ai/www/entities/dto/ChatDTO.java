package com.qwen.ai.www.entities.dto;

import java.util.List;

public class ChatDTO {
    private String message;  //用户输入内容.
    private String systemRole;  //模型角色.
    private List<ChatMessage> history;  //历史信息.

    public static class ChatMessage{
        private String role;  //角色：用户，AI助手.
        private String content;  //内容：用户输入的问题，AI助手回答的内容.

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSystemRole() {
        return systemRole;
    }

    public void setSystemRole(String systemRole) {
        this.systemRole = systemRole;
    }

    public List<ChatMessage> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessage> history) {
        this.history = history;
    }
}
