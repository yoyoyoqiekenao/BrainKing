package com.example.brainking.mine.mine;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.UserInfoModel;

public interface MineView extends BaseView {
    void getUserInfoSuccess(UserInfoModel model);

    void getUserInfoFail(String msg);
}
