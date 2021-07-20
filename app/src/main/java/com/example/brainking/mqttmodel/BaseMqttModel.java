package com.example.brainking.mqttmodel;

import com.jeremyliao.liveeventbus.core.LiveEvent;

public class BaseMqttModel implements LiveEvent {
    private Object object;

    @Override
    public String toString() {
        return "BaseMqttModel{" +
                "object=" + object +
                '}';
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
