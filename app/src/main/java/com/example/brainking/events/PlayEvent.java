package com.example.brainking.events;

public class PlayEvent {
    private boolean isChange;

    public PlayEvent(boolean isChange) {
        this.isChange = isChange;
    }

    public boolean isChange() {
        return isChange;
    }

    public void setChange(boolean change) {
        isChange = change;
    }
}
