package com.qwen.ai.www.service;

import com.qwen.ai.www.entities.dto.ChatDTO;
import org.springframework.http.codec.ServerSentEvent;
import reactor.core.publisher.Flux;

public interface QwenService {
    /*基础对话功能*/
    String generateResponse(String message);

    /*设置大模型角色*/
    String generateResponse(String role,String message);

    /*流式输出:message,role*/
    Flux<String> generateResponseStream(String role,String message);

    /*流式对话（记忆模式）:message消息、role角色*/
    Flux<ServerSentEvent<String>> generateResponseStreamJSON(String role,String userMessage);

    /*多轮历史对话（记忆功能）*/
    Flux<ServerSentEvent<String>> generateChatResponse(ChatDTO request);


}

