package com.example.brainking.battle.joinroom;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchStartModel;

public interface JoinRoomView extends BaseView {
    void matchExitSuccess(MatchStartModel model);

    void fail(String err);
}
