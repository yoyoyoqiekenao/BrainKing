package com.example.brainking.battle.battleready;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.CancelRoomModel;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.model.MatchStartModel;

public interface BattleReadyView extends BaseView {

    void multiReadySuccess(CreateRoomModel model);

    void multiReadyFail(String msg);

    void matchExitSuccess(CancelRoomModel model);

    void fail(String err);
}
