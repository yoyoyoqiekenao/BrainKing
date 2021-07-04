package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageListModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataDTO> data;

    @Override
    public String toString() {
        return "MessageListModel{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }

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

    public static class DataDTO {
        @SerializedName("userId")
        private Integer userId;
        @SerializedName("name")
        private String name;
        @SerializedName("avatar")
        private String avatar;
        @SerializedName("type")
        private Integer type;
        @SerializedName("msg")
        private String msg;
        @SerializedName("status")
        private Integer status;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "userId=" + userId +
                    ", name='" + name + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", type=" + type +
                    ", msg='" + msg + '\'' +
                    ", status=" + status +
                    '}';
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
    }
}
