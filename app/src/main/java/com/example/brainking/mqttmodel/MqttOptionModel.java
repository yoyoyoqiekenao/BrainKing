package com.example.brainking.mqttmodel;

import com.google.gson.annotations.SerializedName;

import java.security.PrivateKey;
import java.util.List;

//题库
public class MqttOptionModel {

    @SerializedName("id")
    public String id;
    @SerializedName("quType")
    public String quType;
    @SerializedName("title")
    public String title;
    @SerializedName("type")
    public String type;
    @SerializedName("option")
    public List<OptionDTO> option;
    @SerializedName("currentIndex")
    private String currentIndex;
    @SerializedName("total")
    private String total;

    public String getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(String currentIndex) {
        this.currentIndex = currentIndex;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuType() {
        return quType;
    }

    public void setQuType(String quType) {
        this.quType = quType;
    }

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
        public String content;
        @SerializedName("id")
        public String id;
        @SerializedName("isRight")
        public Boolean isRight;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Boolean getRight() {
            return isRight;
        }

        public void setRight(Boolean right) {
            isRight = right;
        }
    }
}
