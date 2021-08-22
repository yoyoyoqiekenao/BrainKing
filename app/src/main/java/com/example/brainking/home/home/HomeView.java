package com.example.brainking.home.home;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.LearnListModel;
import com.example.brainking.model.UserInfoModel;

/**
 * @author : 徐无敌
 * date   : 2021/6/1615:40
 * desc   :
 */
public interface HomeView extends BaseView {

    void getLearnListSuccess(LearnListModel model);

    void getLearnListFail(String msg);

    void getUserInfoSuccess(UserInfoModel model);

    void getUserInfoFail(String msg);

    void goReLogin();
}
