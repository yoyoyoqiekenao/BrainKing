package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttReadyModel {

    @SerializedName("multiplayer")
    public Boolean multiplayer;
    @SerializedName("players")
    public List<PlayersDTO> players;
    @SerializedName("roomId")
    public String roomId;
    @SerializedName("type")
    public String type;

    @Override
    public String toString() {
        return "MqttReadyModel{" +
                "multiplayer=" + multiplayer +
                ", players=" + players +
                ", roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Boolean getMultiplayer() {
        return multiplayer;
    }

    public void setMultiplayer(Boolean multiplayer) {
        this.multiplayer = multiplayer;
    }

    public List<PlayersDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayersDTO> players) {
        this.players = players;
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

    public static class PlayersDTO {
        @SerializedName("avatar")
        public String avatar;
        @SerializedName("score")
        public String score;
        @SerializedName("userId")
        public String userId;
        @SerializedName("userName")
        public String userName;
        @SerializedName("fees")
        private String fees;


        public String getFees() {
            return fees;
        }

        public void setFees(String fees) {
            this.fees = fees;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
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
}
