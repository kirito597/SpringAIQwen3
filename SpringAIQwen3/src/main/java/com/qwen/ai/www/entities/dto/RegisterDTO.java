package com.qwen.ai.www.entities.dto;

public class RegisterDTO {
    private String userPhone;
    private String password;
    private String baseImage;

    public RegisterDTO() {
    }

    public RegisterDTO(String userPhone, String password, String baseImage) {
        this.userPhone = userPhone;
        this.password = password;
        this.baseImage = baseImage;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }
}
