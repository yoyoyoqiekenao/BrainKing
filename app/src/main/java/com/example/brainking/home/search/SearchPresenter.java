package com.example.brainking.home.search;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.SearchModel;
import com.example.brainking.net.ApiCallback;

public class SearchPresenter extends BasePresenter<SearchView> {
    public SearchPresenter(SearchView view) {
        attachView(view);
    }

    void search(String key, int pageNum) {
        addSubscription(apiStores.searchPoems(key, pageNum), new ApiCallback<SearchModel>() {
            @Override
            public void onSuccess(SearchModel model) {
                if (200 == model.getCode()) {
                    baseView.searchSuccess(model);
                } else {
                    baseView.searchFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.searchFail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}
