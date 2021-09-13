package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttPvpModel {

    @SerializedName("multiplayer")
    public Boolean multiplayer;
    @SerializedName("roomId")
    public String roomId;
    @SerializedName("type")
    public String type;
    @SerializedName("players")
    public List<?> players;

    public Boolean getMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<?> getPlayers() {
        return players;
    }

    public void setPlayers(List<?> players) {
        this.players = players;
    }
}
