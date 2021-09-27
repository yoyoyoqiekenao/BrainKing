package com.example.brainking.home.userinfo;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.UpdateUserInfoModel;

public interface UserInfoView extends BaseView {
    void updateSuccess(UpdateUserInfoModel model);

    void fail(String err);
}
