package com.example.brainking.model;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewDetailModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private List<DataDTO> data;

    @Override
    public String toString() {
        return "NewDetailModel{" +
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

    public static class DataDTO implements MultiItemEntity {
        @SerializedName("id")
        private Integer id;
        @SerializedName("fromId")
        private Integer fromId;
        @SerializedName("toId")
        private Integer toId;
        @SerializedName("type")
        private Integer type;
        @SerializedName("msg")
        private String msg;
        @SerializedName("status")
        private Integer status;
        @SerializedName("flag")
        private Boolean flag;
        @SerializedName("gmtCreate")
        private String gmtCreate;
        @SerializedName("fromUserAvatar")
        private String fromUserAvatar;
        @SerializedName("fromUserName")
        private String fromUserName;
        @SerializedName("toUserName")
        private String toUserName;
        @SerializedName("toUserAvatar")
        private String toUserAvatar;

        @Override
        public String toString() {
            return "DataDTO{" +
                    "id=" + id +
                    ", fromId=" + fromId +
                    ", toId=" + toId +
                    ", type=" + type +
                    ", msg='" + msg + '\'' +
                    ", status=" + status +
                    ", flag=" + flag +
                    ", gmtCreate='" + gmtCreate + '\'' +
                    ", fromUserAvatar='" + fromUserAvatar + '\'' +
                    ", fromUserName='" + fromUserName + '\'' +
                    ", toUserName='" + toUserName + '\'' +
                    ", toUserAvatar='" + toUserAvatar + '\'' +
                    '}';
        }

        public String getFromUserAvatar() {
            return fromUserAvatar;
        }

        public void setFromUserAvatar(String fromUserAvatar) {
            this.fromUserAvatar = fromUserAvatar;
        }

        public String getFromUserName() {
            return fromUserName;
        }

        public void setFromUserName(String fromUserName) {
            this.fromUserName = fromUserName;
        }

        public String getToUserName() {
            return toUserName;
        }

        public void setToUserName(String toUserName) {
            this.toUserName = toUserName;
        }

        public String getToUserAvatar() {
            return toUserAvatar;
        }

        public void setToUserAvatar(String toUserAvatar) {
            this.toUserAvatar = toUserAvatar;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getFromId() {
            return fromId;
        }

        public void setFromId(Integer fromId) {
            this.fromId = fromId;
        }

        public Integer getToId() {
            return toId;
        }

        public void setToId(Integer toId) {
            this.toId = toId;
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

        public Boolean isFlag() {
            return flag;
        }

        public void setFlag(Boolean flag) {
            this.flag = flag;
        }

        public String getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(String gmtCreate) {
            this.gmtCreate = gmtCreate;
        }

        @Override
        public int getItemType() {
            if (isFlag() == true) {
                return 1;
            } else {
                return 0;
            }

        }
    }
}
