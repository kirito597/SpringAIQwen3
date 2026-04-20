package com.qwen.ai.www.Controller;

import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.User;
import com.qwen.ai.www.mapper.UserMapper;
import com.qwen.ai.www.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller//将这个类标注为控制器类，用@Controller注解标注，用来与前端用户进行数据交互或者视图（前端代码）转发.
@RequestMapping("/api/user")//表示访问的接口.
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/selectAll")//运行Spring Boot.
    @ResponseBody//将后端数据以json格式发送给前端（数据交互），当不使用这个注解时，默认做视图转发.
    public List<User> selectAll(){
        List<User> users = userService.selectAll();
        return users;
    }

    /*根据用户id进行修改*/
    @RequestMapping("/updateById")
    @ResponseBody
    public String updateById(User user){
        String str = userService.updateById(user);
        return str;
    }

    /*根据用户id进行删除*/
    @ResponseBody
    @RequestMapping("/deleteById")
    public String deleteById(Integer userId){
        return userService.deleteById(userId);
    }

    @ResponseBody
    @RequestMapping("/deleteByIds")
    public String deleteByIds(Integer userId){
        return userService.deleteByIds(userId);
    }

    /*添加功能*/
    @ResponseBody
    @RequestMapping("/insertUser")
    public ResultVO<Integer> insertUser(User user){
        return userService.insertUser(user);
    }

}
