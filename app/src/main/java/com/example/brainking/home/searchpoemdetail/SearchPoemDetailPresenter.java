package com.example.brainking.home.searchpoemdetail;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.SearchPoemDetailModel;
import com.example.brainking.net.ApiCallback;

public class SearchPoemDetailPresenter extends BasePresenter<SearchPoemDetailView> {
    public SearchPoemDetailPresenter(SearchPoemDetailView view) {
        attachView(view);
    }

    void getSearchPoemDetail(int id) {
        baseView.showLoading();
        addSubscription(apiStores.getSearchPoemDetail(id), new ApiCallback<SearchPoemDetailModel>() {
            @Override
            public void onSuccess(SearchPoemDetailModel model) {
                if (200 == model.getCode()) {
                    baseView.getSearchPoemDetailSuccess(model);
                } else {
                    baseView.getSearchPoemDetailFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.getSearchPoemDetailFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}
