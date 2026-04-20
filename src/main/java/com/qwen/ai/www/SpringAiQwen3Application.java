package com.qwen.ai.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*Spring Boot程序员的入口*/
/*使用SpringBootApplication标注的类，就是启动类*/
@SpringBootApplication
public class SpringAiQwen3Application {
    /*通过Spring框架的IOC容器进行运行（自动装配）*/
    public static void main(String[] args) {
        SpringApplication.run(SpringAiQwen3Application.class, args);
    }

}
