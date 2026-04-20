package com.qwen.ai.www.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qwen.ai.www.entities.models.User;
import org.apache.ibatis.annotations.Mapper;

/*作用：将mapper接口交给Spring IOC容器管理，作用与启动类注解@MapperScan（"com.qwen.ai.www.mapper"）一样*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /*BaseMapper<>接口中，自带删除，添加，查询，修改功能，不需要手写SQL语句*/
}
