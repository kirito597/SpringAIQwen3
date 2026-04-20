package com.qwen.ai.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.History;
import com.qwen.ai.www.mapper.HistoryMapper;
import com.qwen.ai.www.service.HistoryService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class HistoryServiceImpl implements HistoryService {
    @Resource
    private HistoryMapper historyMapper;

    /*将对话内容存储到数据库*/
    @Override
    public ResultVO<Integer> saveToMySQL(History history) {
        //判断是否为新对话，如果是新对话sessionId=0或者等于空.
        if(history.getSessionId()==null || history.getSessionId() <= 0){
            history.setSessionId(0);
            historyMapper.insert(history);  //把history插入到数据库中.
            //用自身ID作为SessionId.
            Integer id = history.getId();
            history.setSessionId(id);
            historyMapper.updateById(history);
            return new ResultVO<>(200,"success",id);
        }
        //不是新对话内容.
        historyMapper.insert(history);
        return new ResultVO<>(200,"success",history.getSessionId());
    }

    /*根据sessionId获取值（存入一个集合中，一个对话框分别存入一个集合）*/
    @Override
    public ResultVO<List<Map<String, Object>>> switchHistory(Integer sessionId) {
        if(sessionId==null || sessionId<=0){
            return new ResultVO<>(200,"success",new ArrayList<>());
        }
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        //orderByAsc根据时间进行排序，倒序.
        queryWrapper.eq(History::getSessionId,sessionId).orderByAsc(History::getCreateTime);
        List<History> history = historyMapper.selectList(queryWrapper);
        List<Map<String, Object>> result = new ArrayList<>();
        for (History h : history) {
            result.add(Map.of("role","user","type","text","content",h.getQuestion()));
            result.add(Map.of("role","assistant","type","text","content",h.getAnswer()));
        }
        return new ResultVO<>(200,"success",result);
    }

    /*根据登录用户查询用户历史消息*/

    @Override
    public ResultVO<List<History>> getHistory(String username) {
        LambdaQueryWrapper<History> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(History::getUsername,username)
//                .apply("session_id=:id")
                .orderByAsc(History::getCreateTime);
        List<History> histories = historyMapper.selectList(queryWrapper);
        return new ResultVO<>(200,"success",histories);
    }
}
