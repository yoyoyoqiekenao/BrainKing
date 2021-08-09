package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttBattleDetailModel {

    @SerializedName("roomId")
    public Long roomId;
    @SerializedName("type")
    public String type;
    @SerializedName("waitingUser")
    public List<WaitingUserDTO> waitingUser;

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

    public List<WaitingUserDTO> getWaitingUser() {
        return waitingUser;
    }

    public void setWaitingUser(List<WaitingUserDTO> waitingUser) {
        this.waitingUser = waitingUser;
    }

    public static class WaitingUserDTO {
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("email")
        public String email;
        @SerializedName("nickName")
        public String nickName;
        @SerializedName("openid")
        public String openid;
        @SerializedName("phonenumber")
        public String phonenumber;
        @SerializedName("prepare")
        public Boolean prepare;
        @SerializedName("score")
        public Integer score;
        @SerializedName("sex")
        public String sex;
        @SerializedName("userId")
        public Integer userId;
        @SerializedName("userName")
        public String userName;
        @SerializedName("userType")
        public Integer userType;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
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

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getUserType() {
            return userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }
    }
}
