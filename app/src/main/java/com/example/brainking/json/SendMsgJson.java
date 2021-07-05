package com.example.brainking.json;

public class SendMsgJson {
    private String msg;
    private int toId;
    private int type;

    @Override
    public String toString() {
        return "SendMsgJson{" +
                "msg='" + msg + '\'' +
                ", toId=" + toId +
                ", type=" + type +
                '}';
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
