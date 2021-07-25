package com.example.brainking.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchAnswerModel {

    @SerializedName("msg")
    private String msg;
    @SerializedName("code")
    private Integer code;
    @SerializedName("data")
    private DataDTO data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public static class DataDTO {
        @SerializedName("score")
        private Integer score;
        @SerializedName("totalScore")
        private Integer totalScore;
        @SerializedName("rightAnswer")
        private List<String> rightAnswer;

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        public Integer getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(Integer totalScore) {
            this.totalScore = totalScore;
        }

        public List<String> getRightAnswer() {
            return rightAnswer;
        }

        public void setRightAnswer(List<String> rightAnswer) {
            this.rightAnswer = rightAnswer;
        }
    }
}
