package com.qwen.ai.www.service.impl;

import com.baidu.aip.face.AipFace;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qwen.ai.www.VO.ResultVO;
import com.qwen.ai.www.entities.models.User;
import com.qwen.ai.www.mapper.UserMapper;
import com.qwen.ai.www.service.AccService;
import jakarta.annotation.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class AccServiceImpl implements AccService {
    @Resource
    private UserMapper userMapper;

    /*
     * 一般登录，根据手机号和密码进行登录，需要返回用户数据
     * 使用手机号进行登录，使用手机号去数据库中查询数据，如果数据为空：手机号为空或者手机号输入错误；
     * 查询数据不为空，判断密码是否与数据库匹配，如果不匹配：密码错误；
     * 密码匹配：判断用户状态，用户状态为1：正常登录成功；为0：异常，不能登录
     * */

    @Override
    public ResultVO<User> loginPwd(String userPhone, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>();
        queryWrapper.eq(User::getUserPhone, userPhone);

        User user = userMapper.selectOne(queryWrapper); //执行查询操作
        //判断用户数据是否为空
        if (user == null) {
            return new ResultVO<>(400, "用户未注册或手机号输入错误");
        }
        //用户不为空，判断密码
        if (user.getPassword().equals(password)) {
            //密码正确
            if (user.getUserStatus() == 1){
                //状态正确
                return new ResultVO<>(200, "正在登录", user);
            }
            //状态异常
            return new ResultVO<>(400, "账号状态异常");
        }
        return new ResultVO<>(400, "密码错误");
    }

    //注入百度智能云的人脸
    @Resource
    private AipFace aipFace;
    /*
     * 人脸注册，输入用户手机号、密码、人脸数据
     * */
    @Override
    public ResultVO<Integer> register(String userPhone, String password, String baseImage) {
        //判断当前注册的手机号是否已经被使用（查询）
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserPhone, userPhone);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return new ResultVO(400, "手机号已注册!");
        }
        //数据库没有手机号，正常注册
        //调用百度智能云存储人脸信息
        JSONObject response = aipFace.addUser(baseImage, "BASE64", "default", userPhone, null );
        if(response.has("error_code") && response.getInt("error_code") != 0){
            return new ResultVO<>(400, "人脸注册失败，请重试");
        }
        //将返回的数据存入数据库（判断人脸数据是否存在）
        if(!response.has("result") || !response.getJSONObject("result").has("face_token")){
            return new ResultVO<>(400, "未检测到人脸数据!");
        }
        //将数据存入数据库
        //获取百度智能云的faceToken
        String faceId = response.getJSONObject("result").getString("face_token");
        User user1 = new User();
        user1.setUserPhone(userPhone);
        user1.setPassword(password);
        user1.setFaceId(faceId);
        int i = userMapper.insert(user1);
        if(i>0){
            return new ResultVO<>(200, "账号注册成功", i);
        }
        return new ResultVO<>(400, "账户注册失败!");
    }

    /*
     * 人脸登录，只需要人脸数据
     * */
    @Override
    public ResultVO<User> faceLogin(String baseImage) {
        //百度智能云获取人脸数据.
        JSONObject res = aipFace.search(baseImage,"BASE64","default",null);
        //判断是否获取成功.
        if(res.has("error_code") && res.getInt("error_code") !=0){
            return new ResultVO<>(400,"人脸识别失败！");
        }
        //检测是否返回用户列表.
        if(!res.has("result") || !res.getJSONObject("result").has("user_list")){
            return new ResultVO<>(400,"未找到用户信息！");
        }
        //获取用户信息.
        JSONObject result = res.getJSONObject("result");//人脸+用户列表.
        JSONArray userList = result.getJSONArray("user_list");//用户列表.
        if(userList.length() == 0){
            return new ResultVO<>(400,"未检测到用户！");
        }
        //获取匹配结果.
        JSONObject userScore = userList.getJSONObject(0);//获取匹配值.
        double score = userScore.getDouble("score");

        if(score <= 85){
            return new ResultVO<>(400,"匹配值低，请调整摄像头！");
        }
        //识别成功，获取手机号.
        String userPhone = userScore.getString("user_id");
        //查询数据库.
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserPhone, userPhone);
        User user =userMapper.selectOne(queryWrapper);
        if(user == null){
            return new ResultVO<>(400,"帐号已被注销");
        }
        if(user.getUserStatus() == 0){
            return new ResultVO<>(400,"账号状态异常");
        }
        return new ResultVO<>(200,"正在登录！",user);
    }
}