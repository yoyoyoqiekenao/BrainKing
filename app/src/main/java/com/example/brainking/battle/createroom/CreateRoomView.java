package com.example.brainking.battle.createroom;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.MatchStartModel;

public interface CreateRoomView extends BaseView {

    void matchStartSuccess(CreateRoomModel matchStartModel);

    void fail(String err);

    void reConnect();
}
