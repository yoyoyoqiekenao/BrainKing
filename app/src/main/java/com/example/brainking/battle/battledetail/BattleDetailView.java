package com.example.brainking.battle.battledetail;

import com.example.brainking.base.BaseView;

public interface BattleDetailView extends BaseView {

    void multiReadySuccess();

    void multiReadyFail(String msg);
}
