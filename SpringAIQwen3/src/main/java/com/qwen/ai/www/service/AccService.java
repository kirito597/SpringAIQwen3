package com.qwen.ai.www.service;

import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.User;

public interface AccService {
    /*一般登录，根据手机号码和密码进行登录，返回值需要返回用户数据
    * 使用手机号进行登录，使用手机号去数据库中查询数据，如果数据为空（手机号为空或者手机号输入错误），
    * 如果数据不为空，判断密码是否与数据库匹配，如果不匹配密码错误，
    * 密码匹配，就判断用户状态（用户状态为1，正常 登录，0，异常 不能登录）*/
    ResultVO<User>loginPwd(String userPhone,String password);
    /*人脸注册*/
    ResultVO<Integer> register(String userPhone,String password,String baseImage);
    /*人脸识别*/
    ResultVO<User>faceLogin(String baseImage);

}
