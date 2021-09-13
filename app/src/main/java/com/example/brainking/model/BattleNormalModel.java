package com.example.brainking.model;

import java.io.Serializable;

public class BattleNormalModel implements Serializable {
    private String name;
    private String img;
    private String userId;

    public BattleNormalModel(String name, String img, String userId) {
        this.name = name;
        this.img = img;
        this.userId = userId;
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
}
