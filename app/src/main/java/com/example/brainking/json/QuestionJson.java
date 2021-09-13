package com.example.brainking.json;

import java.util.List;

public class QuestionJson {
    private List<String> list;
    private int quType;
    private String title;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getQuType() {
        return quType;
    }

    public void setQuType(int quType) {
        this.quType = quType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
