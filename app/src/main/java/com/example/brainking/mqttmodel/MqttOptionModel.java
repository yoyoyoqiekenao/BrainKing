package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//题库
public class MqttOptionModel {

    @SerializedName("title")
    private String title;
    @SerializedName("type")
    private String type;
    @SerializedName("option")
    private List<OptionDTO> option;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OptionDTO> getOption() {
        return option;
    }

    public void setOption(List<OptionDTO> option) {
        this.option = option;
    }

    public static class OptionDTO {
        @SerializedName("content")
        private String content;
        @SerializedName("isRight")
        private Boolean isRight;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Boolean isIsRight() {
            return isRight;
        }

        public void setIsRight(Boolean isRight) {
            this.isRight = isRight;
        }
    }
}