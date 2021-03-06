package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class MatchStartModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private String data;

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

    public String isData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
