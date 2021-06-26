package com.example.brainking.home.home;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.LearnListModel;

/**
 * @author : 徐无敌
 * date   : 2021/6/1615:40
 * desc   :
 */
public interface HomeView extends BaseView {

    void getLearnListSuccess(LearnListModel model);

    void getLearnListFail(String msg);
}
