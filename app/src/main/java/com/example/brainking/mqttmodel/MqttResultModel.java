package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttResultModel {

    @SerializedName("addScore")
    public String addScore;
    @SerializedName("multiplayer")
    public Boolean multiplayer;
    @SerializedName("resultType")
    public String resultType;
    @SerializedName("type")
    public String type;
    @SerializedName("userId")
    public String userId;
    @SerializedName("player")
    public List<PlayerDTO> player;

    public String getAddScore() {
        return addScore;
    }

    public void setAddScore(String addScore) {
        this.addScore = addScore;
    }

    public Boolean getMultiplayer() {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<PlayerDTO> getPlayer() {
        return player;
    }

    public void setPlayer(List<PlayerDTO> player) {
        this.player = player;
    }

    @Override
    public String toString() {
        return "MqttResultModel{" +
                "addScore=" + addScore +
                ", multiplayer=" + multiplayer +
                ", resultType='" + resultType + '\'' +
                ", type='" + type + '\'' +
                ", userId=" + userId +
                ", player=" + player +
                '}';
    }

    public static class PlayerDTO {
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("name")
        public String name;
        @SerializedName("score")
        public String score;

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

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "PlayerDTO{" +
                    "avatar='" + avatar + '\'' +
                    ", name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
