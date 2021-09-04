package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class MessageReadModel {

    @SerializedName("msg")
    public String msg;
    @SerializedName("code")
    public Integer code;
    @SerializedName("data")
    public Boolean data;

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

    public Boolean getData() {
        return data;
    }

    public void setData(Boolean data) {
        this.data = data;
    }
}
