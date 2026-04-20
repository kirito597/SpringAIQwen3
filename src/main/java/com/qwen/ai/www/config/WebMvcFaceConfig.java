package com.qwen.ai.www.config;

import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//配置类：配置为全局变量，注入Spring IOC容器中.
@Configuration
public class WebMvcFaceConfig {

    //@Value注解:获取配置文件信息.
    @Value("${baidu.api.appId}")
    private String appId;  //声明配置类的私有变量，获取配置类信息.
    @Value("${baidu.api.apiKey}")
    private String apiKey;
    @Value("${baidu.api.secretKey}")
    private String secretKey;

    //创建全局连接对象.
    @Bean
    public AipFace aipFace() {
        AipFace aipFace = new AipFace(appId, apiKey, secretKey);
        //设置连接参数.
        aipFace.setConnectionTimeoutInMillis(3000);  //3s延迟，超过3s报错.
        aipFace.setSocketTimeoutInMillis(10000);  //10s网络响应时间.
        return aipFace;
    }
}
