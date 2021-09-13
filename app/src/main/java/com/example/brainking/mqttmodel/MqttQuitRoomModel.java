package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

public class MqttQuitRoomModel {

    @SerializedName("type")
    public String type;
    @SerializedName("userId")
    public String userId;
    @SerializedName("userName")
    public String userName;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
