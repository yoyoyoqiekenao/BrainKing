package com.example.brainking.json;

import java.util.List;

public class MatchAnswerJson {
    private List<String> answers;
    private String roomId;

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
}
