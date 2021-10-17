package com.example.brainking.battle.friend_pk;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.UserInfoModel;

public interface FriendPkView extends BaseView {

    void getUserInfoSuccess(UserInfoModel model);

    void getUserInfoFail(String msg);
}
