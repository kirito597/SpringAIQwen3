package com.qwen.ai.www.entities.models;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_tb")//显示指定数据库表的名字.
public class User {
    //变量类型：修饰符 数据类型 变量名称.
    //面向对象编程，“1”，包装类：就是将基本数据类型转化成一个对象，供前端或者接收前端对象数据（int 包装类 Integer）.
    //指定数据库主键：@TableId(value = "数据库主键",type = IdType.AUTO)，IdType.AUTO：数据库自动递增.
    @TableId(value = "user_id",type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPhone;
    private String userEmail;
    private Integer userStatus;
    private String password;
    private String faceId;
    private String userImage;

    //构造器作用：创建对象（Java默认提供无参构造器，所以可以创建对象，但是一旦提供构造器，那么就会覆盖无参构造器）.
    //无参构造器.
    public User() {
    }
    //全参构造器.
    public User(Integer userId, String userName, String userPhone, String userEmail, Integer userStatus, String password, String faceId, String userImage) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userEmail = userEmail;
        this.userStatus = userStatus;
        this.password = password;
        this.faceId = faceId;
        this.userImage = userImage;
    }

    /*private修饰的变量表示封装，封装后，这个变量只能在该类中访问，在其他类无法访问
    * 所以，需要提供一个方法来供其他类访问（getter方法），
    * 其他类修改这个变量时，需要提供一个setter方法，才能进行修改数据*/
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFaceId() {
        return faceId;
    }

    public void setFaceId(String faceId) {
        this.faceId = faceId;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
}
