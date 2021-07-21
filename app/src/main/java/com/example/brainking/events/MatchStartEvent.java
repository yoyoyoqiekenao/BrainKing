package com.example.brainking.events;


import java.io.Serializable;

//开始匹配  自动创建房间
public class MatchStartEvent implements Serializable {

    private String msg;

    @Override
    public String toString() {
        return "MatchStartEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
