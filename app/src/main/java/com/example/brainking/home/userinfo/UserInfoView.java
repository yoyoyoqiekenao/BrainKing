package com.example.brainking.home.userinfo;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.UpdateUserInfoModel;
import com.example.brainking.model.UploadModel;

public interface UserInfoView extends BaseView {
    void updateSuccess(UpdateUserInfoModel model);

    void fail(String err);

    void uploadSuccess(UploadModel model);
}
