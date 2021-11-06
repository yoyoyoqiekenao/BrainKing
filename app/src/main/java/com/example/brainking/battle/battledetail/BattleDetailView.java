package com.example.brainking.battle.battledetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;
import com.example.brainking.model.ReConnectModel;

public interface BattleDetailView extends BaseView {

    void matchAnswerSuccess(MatchAnswerModel matchAnswerModel);

    void fail(String err);

    void reConnectSuccess(ReConnectModel model);
}
