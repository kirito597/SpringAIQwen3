package com.qwen.ai.www.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.User;
import com.qwen.ai.www.mapper.UserMapper;
import com.qwen.ai.www.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//与@Mapper注解相同，将这个类交给Spring IOC容器管理.
public class UserServiceImpl implements UserService {
    /*Java类实现接口时，需要实现接口中所有普通方法*/
    @Resource
    private UserMapper userMapper;

    public List<User> selectAll(){
        //相当于执行select * from user_tb.
        List<User> users = userMapper.selectList(null);
        if(users.size()>0){
            return users;
        }else{
            return null;
        }
    }

    public String updateById(User user){
        //条件构造器.
        LambdaQueryWrapper<User> queryWrapper = null;
        queryWrapper = new LambdaQueryWrapper<>();//where条件.
        //1.eq：等同于sql语句中的等号.
        //2User::getUserId等同于user_id.
        queryWrapper.eq(User::getUserId,user.getUserId());//where user_id = user.getUserId.

        //调用mapper属性接口.
        int i = userMapper.update(user,queryWrapper);
        if(i > 0){
            return "成功修改"+i+"条数据";
        }
        return "修改失败";
    }

    /*根据用户id进行删除（control+i快速实现接口方法）*/
    @Override
    public String deleteById(Integer userId) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserId,userId);
        //调用Mybatis中自带的删除方法.
        int i = userMapper.delete(queryWrapper);
        if(i > 0)
            return "成功删除"+i+"条数据";
        return "数据删除失败";

    }

    /*使用方法userMapper.updateById(user),userMapper.deleteById(userId)
    *需要显示指定数据库字段的主键
    **/
    public String deleteByIds(Integer userId) {
        int result = userMapper.deleteById(userId);
        if(result > 0)
            return "数据删除成功";
        return "数据删除失败";
    }

    /*添加功能*/
    @Override
    public ResultVO<Integer> insertUser(User user) {
        int i = userMapper.insert(user);
        if(i > 0)
            return new ResultVO<>(200,"数据添加成功",i);
        return new ResultVO<>(400,"数据添加失败");
    }
}
