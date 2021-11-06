package com.example.brainking.home.userinfo;

import android.text.TextUtils;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.UpdateUserInfoJson;
import com.example.brainking.model.UpdateUserInfoModel;
import com.example.brainking.model.UploadModel;
import com.example.brainking.net.ApiCallback;

import java.io.File;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserInfoPresenter extends BasePresenter<UserInfoView> {
    public UserInfoPresenter(UserInfoView view) {
        attachView(view);
    }

    public void getFile(File file) {
        //RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody body = RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), body);
        addSubscription(apiStores.getFile(part), new ApiCallback<UploadModel>() {
            @Override
            public void onSuccess(UploadModel model) {
                if (200 == model.getCode()) {
                    baseView.uploadSuccess(model);
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

    public void updateUserInfo(String nickName, String inviteCode, String avatar, String remark) {
        UpdateUserInfoJson json = new UpdateUserInfoJson();
        json.setAvatar(avatar);
        json.setRemark(remark);
        json.setNickName(nickName);
        if (!TextUtils.isEmpty(inviteCode)) {
            json.setInviteCode(inviteCode);
        }
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
