package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BattleListModel {

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

    public static class DataDTO {
        @SerializedName("roomID")
        public Long roomID;
        @SerializedName("roomName")
        public String roomName;
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("defficultLevel")
        public Integer defficultLevel;

        public Long getRoomID() {
            return roomID;
        }

        public void setRoomID(Long roomID) {
            this.roomID = roomID;
        }

        public String getRoomName() {
            return roomName;
        }

        public void setRoomName(String roomName) {
            this.roomName = roomName;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Integer getDefficultLevel() {
            return defficultLevel;
        }

        public void setDefficultLevel(Integer defficultLevel) {
            this.defficultLevel = defficultLevel;
        }
    }
}
