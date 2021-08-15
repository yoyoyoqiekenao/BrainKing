package com.example.brainking.battle.battleready;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.CreateRoomModel;

public interface BattleReadyView extends BaseView {

    void multiReadySuccess(CreateRoomModel model);

    void multiReadyFail(String msg);
}
