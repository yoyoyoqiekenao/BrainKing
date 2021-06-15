package com.example.brainking.model;

/**
 * @author : 徐无敌
 * date   : 2021/6/1516:50
 * desc   :
 */
public class VerCodeModel {
    private String msg;
    private String code;
    private String uuid;

    @Override
    public String toString() {
        return "VerCodeModel{" +
                "msg='" + msg + '\'' +
                ", code='" + code + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
