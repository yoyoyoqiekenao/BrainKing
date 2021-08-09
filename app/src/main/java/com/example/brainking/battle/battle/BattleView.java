package com.example.brainking.battle.battle;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.BattleListModel;

public interface BattleView extends BaseView {
    void getBattleListSuccess(BattleListModel model);

    void Failed(String msg);
}
