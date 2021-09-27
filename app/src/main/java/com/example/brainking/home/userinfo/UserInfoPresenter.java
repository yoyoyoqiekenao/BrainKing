package com.example.brainking.home.userinfo;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.UpdateUserInfoJson;
import com.example.brainking.model.UpdateUserInfoModel;
import com.example.brainking.net.ApiCallback;

public class UserInfoPresenter extends BasePresenter<UserInfoView> {
    public UserInfoPresenter(UserInfoView view) {
        attachView(view);
    }

    public void updateUserInfo(String nickName, String inviteCode, String avatar, String remark) {
        UpdateUserInfoJson json = new UpdateUserInfoJson();
        json.setAvatar(avatar);
        json.setRemark(remark);
        json.setNickName(nickName);
        json.setInviteCode(inviteCode);
        addSubscription(apiStores.updateUserInfo(json), new ApiCallback<UpdateUserInfoModel>() {


            @Override
            public void onSuccess(UpdateUserInfoModel model) {
                if (200 == model.getCode()) {
                    baseView.updateSuccess(model);
                } else {
                    baseView.fail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.fail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }
}
