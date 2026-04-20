package com.qwen.ai.www.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qwen.ai.www.entities.dto.ChatDTO;
import com.qwen.ai.www.service.QwenService;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QwenServiceImpl implements QwenService {

    private final ChatClient chatClient;  //导入Spring AI封装好的大语言模型（large language model）类.

    //调用Spring的构造方法注入Spring AI，SpringAI为我们提供了一个构造器.
    public QwenServiceImpl(ChatClient.Builder builder, ObjectMapper objectMapper) {  //ChatClient.Builder.
        this.chatClient = builder.build();  //构建Spring AI注入（自动生成全局方法Bean）.
    }

    /*基础对话功能*/
    @Override
    public String generateResponse(String message) {
        ChatClient.ChatClientRequestSpec prompt = chatClient.prompt(message);//prompt：传给ollama大模型平台的参数.

//        System.out.println(prompt);//SpringAI提供的方法（接收前端用户输入的参数）.
//        System.out.println(prompt.call());//发送请求给ollama服务器（11434）.
//        System.out.println(prompt.call().content());//content():接收ollama服务器发送给用户的数据.

        ChatClient.CallResponseSpec call = prompt.call();
        String result = call.content();//接收ollama服务器大模型预测的信息，将其发送给用户.
        return result;
        //return chatClient.prompt(message).call.
    }

    /*基础功能
    * message:用户输入问题
    * role:大模型性格角色
    * */
    @Override
    public String generateResponse(String role, String message) {

        ChatClient.ChatClientRequestSpec prompt = chatClient.prompt();  //创建对话请求.
        String result = prompt
                .system(role)  //设置大模型角色信息.
                .user(message)  //设置用户信息.
                .call()  //发送请求给ollama服务器（11434）.
                .content();  //接收ollama返回的消息.

        return result;
    }

    /*流式输出:message,role*/
    @Override
    public Flux<String> generateResponseStream(String role, String message) {
        Flux<String> content = chatClient.prompt()
                .system(role)
                .user(message)
                .stream()
                .content();

        return content;
    }

    /*流式对话（记忆模式）:message消息、role角色*/
    @Resource
    private ObjectMapper objectMapper;
    @Override
    public Flux<ServerSentEvent<String>> generateResponseStreamJSON(String role, String userMessage) {
        Flux<ServerSentEvent<String>> content = chatClient.prompt()
                .system(role)
                .user(userMessage)
                .stream()
                .content()
                .map(token->{
                    try {
                        //直接构造JSON对象，比如"{content:你}，{content:好}"，因为前端使用markdown解析，不支持SSE，支持JSON.
                        String jsonPayload = objectMapper.writeValueAsString(Map.of("content", token));
                        return ServerSentEvent.<String>builder().data(jsonPayload).build();
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                });
        return content;
    }

    /*多轮历史对话（记忆功能）*/
    public Flux<ServerSentEvent<String>> generateChatResponse(ChatDTO request){
        ChatClient.ChatClientRequestSpec prompt = chatClient.prompt();
        if(request.getSystemRole() != null && request.getSystemRole().trim().isEmpty()){
            prompt = prompt.system(request.getSystemRole());
        }
        if(request.getHistory() != null && !request.getHistory().isEmpty()){
            List<Message> messages = convertToMessage(request.getHistory());
            prompt = prompt.messages(messages);
        }
        prompt = prompt.user(request.getMessage());
        Flux<ServerSentEvent<String>> result = prompt
                .stream()
                .content()
                .map(token->{
                    try {
                        String jsonPayload = objectMapper.writeValueAsString(Map.of("content", token));
                        return ServerSentEvent.<String>builder().data(jsonPayload).build();
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                });
        return  result;
    }

    /*处理历史消息，区分用户信息还是AI助手信息*/
    private List<Message> convertToMessage(List<ChatDTO.ChatMessage> history){
        List<Message> messages = new ArrayList<>();
        for(ChatDTO.ChatMessage msg : history){
            if("user".equals(msg.getRole())){
                messages.add(new UserMessage(msg.getContent()));
            }else if("assistant".equals(msg.getRole())){
                messages.add(new SystemMessage(msg.getContent()));
            }
        }
        return messages;
    }

}


