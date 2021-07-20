package com.example.brainking.battle.createroom;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchStartModel;

public interface CreateRoomView extends BaseView {

    void matchStartSuccess(MatchStartModel matchStartModel);

    void fail(String err);
}
