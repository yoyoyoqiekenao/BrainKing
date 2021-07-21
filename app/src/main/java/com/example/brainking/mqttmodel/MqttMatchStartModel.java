package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

public class MqttMatchStartModel {

    @SerializedName("roomId")
    private String roomId;
    @SerializedName("type")
    private String type;
    @SerializedName("user")
    private UserDTO user;

    @Override
    public String toString() {
        return "MqttMatchStartModel{" +
                "roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", user=" + user +
                '}';
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public static class UserDTO {
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("openid")
        private String openid;
        @SerializedName("score")
        private Integer score;
        @SerializedName("userId")
        private Long userId;

        @Override
        public String toString() {
            return "UserDTO{" +
                    "avatar='" + avatar + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", openid='" + openid + '\'' +
                    ", score=" + score +
                    ", userId=" + userId +
                    '}';
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }
}
