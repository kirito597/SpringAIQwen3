package com.qwen.ai.www.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller  //视图控制器没有@ResponseBody注解.
public class ViewController {
    /*视图名称，就是返回前端文件的名称*/
    @RequestMapping("/")  // /表示默认请求.
    public String goIndex(){
        return "index";  //默认从templates文件夹开始访问.
    }

    @RequestMapping("/pwd_login")
    public String goPwdLogin(){
        return "acco/pwd_login";
    }

    /*访问主页视图*/
    @RequestMapping("/go.regis")
    public String goRegister(){
        return "acco/register";
    }

    /*访问人脸识别html*/
    @RequestMapping("/face.login")
    public String goFaceLogin(){
        return "acco/face_login";
    }

    /*访问vue.html*/
    @RequestMapping("/vue")
    public String goVue(){
        return "/vue";
    }

    /*访问主页组件*/
    @RequestMapping("/index.page")
    public String goIndexPage(){
        return "pages/index_page";
    }

    /*访问非流式输出页面*/
    @RequestMapping("/qwen_no_stream")
    public String qwenNoStream(){
        return "qwen/qwen_no_stream";
    }

    /*跳转到流式输出页面*/
    @RequestMapping("/qwen_stream")
    public String qwenStream(){
        return "qwen/qwen_stream";
    }

    /*跳转到上下文对话（ollama不支持多轮对话）*/
    @RequestMapping("/qwen_history")
    public String qwenHistoryStream(){
        return "qwen/qwen_history_stream";
    }

    /*跳转个人中心*/
    @RequestMapping("/home")
    public String goHome(){
        return "pages/home";
    }

}
