package com.example.brainking;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.UserInfoModel;

public interface MainView extends BaseView {
    void getUserInfoSuccess(UserInfoModel model);

    void getUserInfoFail(String msg);
}
