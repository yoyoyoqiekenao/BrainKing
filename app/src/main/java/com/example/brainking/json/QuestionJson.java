package com.example.brainking.json;

import java.util.List;

public class QuestionJson {
    private List<String> options;
    private String quType;
    private String title;
    private String right;

    public String getRight() {
        return right;
    }

    public void setRight(String right) {
        this.right = right;
    }

    public List<String> getList() {
        return options;
    }

    public void setList(List<String> list) {
        this.options = list;
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
}
