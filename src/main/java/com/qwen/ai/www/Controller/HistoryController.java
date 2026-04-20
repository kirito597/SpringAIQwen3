package com.qwen.ai.www.Controller;

import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.History;
import com.qwen.ai.www.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/history")
public class HistoryController {
    @Resource
    private HistoryService historyService;

    /*存储到数据库*/
    @ResponseBody
    @RequestMapping("/saveToMySQL")
    public ResultVO<Integer> saveToMySQL(@RequestBody History history){
        return historyService.saveToMySQL(history);
    }

    /*根据sessionId获取值（存入一个集合中，一个对话框分别存入一个集合）*/
    @ResponseBody
    @RequestMapping("/switchHistory")
    ResultVO<List<Map<String,Object>>> switchHistory(Integer sessionId){
        return historyService.switchHistory(sessionId);
    }
    /*根据登录用户查询用户历史消息*/
    @ResponseBody
    @RequestMapping("/getHistory")
    public ResultVO<List<History>> getHistory(String username){
        return historyService.getHistory(username);
    }
}
