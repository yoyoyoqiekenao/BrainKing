package com.example.brainking.battle.battle;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.BattleListModel;
import com.example.brainking.model.JoinRoomModel;

public interface BattleView extends BaseView {
    void getBattleListSuccess(BattleListModel model);

    void Failed(String msg);

    void joinRoomSuccess(JoinRoomModel model);
}
