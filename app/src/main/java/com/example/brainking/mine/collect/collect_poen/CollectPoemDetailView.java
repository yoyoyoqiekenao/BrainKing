package com.example.brainking.mine.collect.collect_poen;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.CollectDetailModel;

public interface CollectPoemDetailView extends BaseView {
    void getDetailSuccess(CollectDetailModel model);

    void fail(String msg);
}
