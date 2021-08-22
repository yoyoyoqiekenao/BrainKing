package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JoinRoomModel   {

    @SerializedName("msg")
    public String msg;
    @SerializedName("code")
    public Integer code;
    @SerializedName("data")
    public List<DataDTO> data;

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

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public static class DataDTO implements Serializable{
        @SerializedName("userId")
        public String userId;
        @SerializedName("deptId")
        public String deptId;
        @SerializedName("userName")
        public String userName;
        @SerializedName("nickName")
        public String nickName;
        @SerializedName("userType")
        public String userType;
        @SerializedName("email")
        public String email;
        @SerializedName("phonenumber")
        public String phonenumber;
        @SerializedName("sex")
        public String sex;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("openid")
        public String openid;
        @SerializedName("location")
        public String location;
        @SerializedName("score")
        public Integer score;
        @SerializedName("remark")
        public String remark;
        @SerializedName("prepare")
        public Boolean prepare;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
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

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
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

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Boolean getPrepare() {
            return prepare;
        }

        public void setPrepare(Boolean prepare) {
            this.prepare = prepare;
        }
    }
}
