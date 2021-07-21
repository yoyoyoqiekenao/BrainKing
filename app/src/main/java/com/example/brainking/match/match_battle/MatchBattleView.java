package com.example.brainking.match.match_battle;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.MatchStartModel;

public interface MatchBattleView extends BaseView {
    void mattchReadySuccess(MatchStartModel model);

    void fail(String err);
}
