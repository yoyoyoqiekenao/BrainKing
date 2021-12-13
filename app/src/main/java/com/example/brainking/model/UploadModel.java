package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

public class UploadModel {

    @SerializedName("msg")
    public String msg;
    @SerializedName("fileName")
    public String fileName;
    @SerializedName("code")
    public Integer code;
    @SerializedName("url")
    public String url;

    @Override
    public String toString() {
        return "UploadModel{" +
                "msg='" + msg + '\'' +
                ", fileName='" + fileName + '\'' +
                ", code=" + code +
                ", url='" + url + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
