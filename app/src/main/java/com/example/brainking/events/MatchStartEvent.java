package com.example.brainking.events;


import com.jeremyliao.liveeventbus.core.LiveEvent;

//开始匹配  自动创建房间
public class MatchStartEvent implements LiveEvent {

    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
