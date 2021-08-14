package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

public class MqttBattleDetailModel  {

    @SerializedName("joinUser")
    public JoinUserDTO joinUser;
    @SerializedName("roomId")
    public Long roomId;
    @SerializedName("type")
    public String type;

    public JoinUserDTO getJoinUser() {
        return joinUser;
    }

    public void setJoinUser(JoinUserDTO joinUser) {
        this.joinUser = joinUser;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class JoinUserDTO {
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("nickName")
        public String nickName;
        @SerializedName("openid")
        public String openid;
        @SerializedName("prepare")
        public Boolean prepare;
        @SerializedName("score")
        public Integer score;
        @SerializedName("userId")
        public Long userId;

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

        public Boolean getPrepare() {
            return prepare;
        }

        public void setPrepare(Boolean prepare) {
            this.prepare = prepare;
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
