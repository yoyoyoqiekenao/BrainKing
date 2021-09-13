package com.example.brainking.battle.battledetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchAnswerModel;
import com.example.brainking.model.MatchStartModel;

public interface BattleDetailView extends BaseView {

    void matchAnswerSuccess(MatchAnswerModel matchAnswerModel);

    void fail(String err);
}
