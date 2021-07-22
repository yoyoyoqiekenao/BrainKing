package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

public class MqttAnswerNoticeModel {

    @SerializedName("choice")
    private Integer choice;
    @SerializedName("score")
    private Integer score;
    @SerializedName("time")
    private Integer time;
    @SerializedName("totalScore")
    private Integer totalScore;
    @SerializedName("type")
    private String type;

    @Override
    public String toString() {
        return "MqttAnswerNoticeModel{" +
                "choice=" + choice +
                ", score=" + score +
                ", time=" + time +
                ", totalScore=" + totalScore +
                ", type='" + type + '\'' +
                '}';
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
