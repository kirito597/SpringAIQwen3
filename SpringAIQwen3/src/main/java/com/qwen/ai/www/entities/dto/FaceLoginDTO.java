package com.qwen.ai.www.entities.dto;

public class FaceLoginDTO {
    private String baseImage;

    public FaceLoginDTO() {
    }

    public FaceLoginDTO(String baseImage) {
        this.baseImage = baseImage;
    }

    public String getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(String baseImage) {
        this.baseImage = baseImage;
    }
}
