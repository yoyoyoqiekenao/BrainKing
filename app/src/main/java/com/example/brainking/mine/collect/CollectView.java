package com.example.brainking.mine.collect;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.CollectListModel;

public interface CollectView extends BaseView {

    void getCollectSuccess(CollectListModel model);

    void getCollectFail(String err);
}
