package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttResultModel {

    @SerializedName("addScore")
    private Integer addScore;
    @SerializedName("multiplayer")
    private Boolean multiplayer;
    @SerializedName("resultType")
    private String resultType;
    @SerializedName("type")
    private String type;
    @SerializedName("userId")
    private Integer userId;
    @SerializedName("player")
    private List<PlayerDTO> player;

    public Integer getAddScore() {
        return addScore;
    }

    public void setAddScore(Integer addScore) {
        this.addScore = addScore;
    }

    public Boolean isMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PlayerDTO> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerDTO> player) {
        this.player = player;
    }

    public static class PlayerDTO {
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("name")
        private String name;
        @SerializedName("score")
        private Integer score;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }
}
