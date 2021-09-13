package com.example.brainking.model;

public class BattleDetailModel {
    private String name;
    private String img;
    private String totalScore;
    private String userId;

    public BattleDetailModel(String name, String img, String totalScore, String userId) {
        this.name = name;
        this.img = img;
        this.userId = userId;
        this.totalScore = totalScore;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }
}
