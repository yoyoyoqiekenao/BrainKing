package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MqttAnswerNoticeModel  {

    @SerializedName("score")
    private Integer score;
    @SerializedName("time")
    private Integer time;
    @SerializedName("totalScore")
    private String totalScore;
    @SerializedName("type")
    private String type;
    @SerializedName("choice")
    private List<String> choice;

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

    public String getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getChoice() {
        return choice;
    }

    public void setChoice(List<String> choice) {
        this.choice = choice;
    }
}
