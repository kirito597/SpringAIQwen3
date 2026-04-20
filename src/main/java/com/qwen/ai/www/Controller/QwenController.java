package com.qwen.ai.www.Controller;

import com.qwen.ai.www.entities.dto.ChatDTO;
import com.qwen.ai.www.entities.dto.GenerateChatStreamDTO;
import com.qwen.ai.www.service.QwenService;
import jakarta.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("/api/qwen")
public class QwenController {

    @Resource
    private QwenService qwenService;

    @GetMapping("/chat")
    @ResponseBody
    public String generateResponse(String message){
        return qwenService.generateResponse(message);
    }

    /*基础功能
     * message:用户输入问题
     * role:大模型性格角色
     * */
    @ResponseBody
    @RequestMapping("/chat-role")
    public String generateResponse(String role,String message){
        return qwenService.generateResponse(role, message);
    }

    /*流式输出:message,role*/
    @ResponseBody
    @RequestMapping(value = "/chat-stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generateResponseStream(String role,String message){
        return qwenService.generateResponseStream(role, message);
    }

    /*流式对话*/
    @ResponseBody
    @PostMapping(value = "/chat_stream_json",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> generateResponseStreamJson(@RequestBody GenerateChatStreamDTO dto){
        return qwenService.generateResponseStreamJSON(dto.getRole(), dto.getUserMessage());
    }

    /*多轮历史对话（记忆功能）*/
    @ResponseBody
    @RequestMapping("/chatHistory")
    public Flux<ServerSentEvent<String>> generateChatResponse(@RequestBody ChatDTO request){
        return qwenService.generateChatResponse(request);
    }



}
