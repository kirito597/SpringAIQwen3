package com.qwen.ai.www.service;

import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.History;

import java.util.List;
import java.util.Map;


public interface HistoryService {
    /*将对话内容存储到数据库*/
    ResultVO<Integer> saveToMySQL(History history);

    /*根据sessionId获取值（存入一个集合中，一个对话框分别存入一个集合）*/
    ResultVO<List<Map<String,Object>>> switchHistory(Integer sessionId);

    /*根据登录用户查询用户历史消息*/
    ResultVO<List<History>> getHistory(String username);
}
