package com.example.brainking.login;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.VerCodeModel;

/**
 * @author : 徐无敌
 * date   : 2021/6/1517:00
 * desc   :
 */
public interface LoginView  extends BaseView {
    void getVerCodeSuccess(VerCodeModel model);

    void getVerCodeFail(String err);
}
