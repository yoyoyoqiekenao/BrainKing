package com.example.brainking.json;

public class CreateBattleRoomJson {
    private String defficultLevel;
    private String limitNum;
    private String roomName;

    public String getDefficultLevel() {
        return defficultLevel;
    }

    public void setDefficultLevel(String defficultLevel) {
        this.defficultLevel = defficultLevel;
    }

    public String getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(String limitNum) {
        this.limitNum = limitNum;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
