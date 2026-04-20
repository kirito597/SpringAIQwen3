package com.qwen.ai.www.VO;

public class ResultVO<T> {
    /*比如：登录成功
    * code:200,msg:"正在登录",data:User
    * 登陆失败
    * code:400,msg:"密码输入错误",data:null
    * */
    private Integer code;
    private String msg;
    private T data;

    //构造器用来实现返回值（成功：全参构造器）.
    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //失败时返回值.
    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
