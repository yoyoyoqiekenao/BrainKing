package com.example.brainking.model;

public class BattleNormalModel {
    private String name;
    private String img;

    @Override
    public String toString() {
        return "BattleNormalModel{" +
                "name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }

    public BattleNormalModel(String name, String img) {
        this.name = name;
        this.img = img;
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
