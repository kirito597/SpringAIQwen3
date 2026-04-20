package com.qwen.ai.www.Controller;

import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.dto.FaceLoginDTO;
import com.qwen.ai.www.entities.dto.RegisterDTO;
import com.qwen.ai.www.entities.models.User;
import com.qwen.ai.www.service.AccService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/acc")
public class AccController {
    @Resource
    private AccService accService;

    /*登录功能*/
    @ResponseBody
    @RequestMapping("/loginPwd")
    public ResultVO<User> getUser(String userPhone,String password){
        ResultVO<User> user = accService.loginPwd(userPhone,password);
        return user;
    }

    /*人脸注册:使用DTO来传参数（Data Transform Object：用来与前端进行数据交互）
    * 在entities文件夹下新建一个Java包（DTO），然后在这个包中新建Java class（RegisterDTO.java）*/
    @ResponseBody
    @PostMapping("/register")//post请求方式，等同于（@RequestMapping(value = "/register",method = RequestMethod.POST)）.
    public ResultVO<Integer> register(@RequestBody RegisterDTO dto){
        //使用DTO（对象）传参数，前端一般使用JSON数据发送请求数据给后端.
        //为了接收前端传到后端的JSON数据，所以后端接口中，声明的对象（DTO），需要使用@RequestBody注解，声明的形参变量对象中.
        return accService.register(dto.getUserPhone(),dto.getPassword(),dto.getBaseImage());
    }

    /*人脸识别*/
    @ResponseBody  //将后端的数据以JSON格式发送给前端.
    @PostMapping("/faceLogin")
    //@RequestBody:接收前端axios传到后端的JSON数据.
    public ResultVO<User> faceLogin(@RequestBody FaceLoginDTO dto){
        return accService.faceLogin(dto.getBaseImage());
    }

}
