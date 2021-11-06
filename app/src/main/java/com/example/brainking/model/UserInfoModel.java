package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class UserInfoModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("userId")
        private Integer userId;
        @SerializedName("userName")
        private String userName;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("email")
        private String email;
        @SerializedName("phonenumber")
        private String phonenumber;
        @SerializedName("sex")
        private String sex;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("inviteCode")
        private Integer inviteCode;
        @SerializedName("location")
        private Object location;
        @SerializedName("level")
        private String level;
        //分子
        @SerializedName("fightinga")
        private String fightinga;
        //分母
        @SerializedName("fightingb")
        private String fightingb;
        @SerializedName("remark")
        private String remark;

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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getInviteCode() {
            return inviteCode;
        }

        public void setInviteCode(Integer inviteCode) {
            this.inviteCode = inviteCode;
        }

        public Object getLocation() {
            return location;
        }

        public void setLocation(Object location) {
            this.location = location;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getFightinga() {
            return fightinga;
        }

        public void setFightinga(String fightinga) {
            this.fightinga = fightinga;
        }

        public String getFightingb() {
            return fightingb;
        }

        public void setFightingb(String fightingb) {
            this.fightingb = fightingb;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
