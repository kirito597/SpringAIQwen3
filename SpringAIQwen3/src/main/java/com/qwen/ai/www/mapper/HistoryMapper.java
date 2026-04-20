package com.qwen.ai.www.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qwen.ai.www.entities.models.History;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
}
