package com.example.brainking.home.search;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.model.SearchHistoryModel;
import com.example.brainking.model.SearchModel;
import com.example.brainking.net.ApiCallback;

public class SearchPresenter extends BasePresenter<SearchView> {
    public SearchPresenter(SearchView view) {
        attachView(view);
    }

    void delAllHistory() {
        addSubscription(apiStores.delAllHistory(), new ApiCallback() {
            @Override
            public void onSuccess(Object model) {
                baseView.deleteSuccess();
            }

            @Override
            public void onFailure(String msg) {
                baseView.searchHistoryFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
    }

    void searchHistory(String userId) {
        addSubscription(apiStores.getSearchHistory(1, 10, userId), new ApiCallback<SearchHistoryModel>() {
            @Override
            public void onSuccess(SearchHistoryModel model) {
                if (200 == model.getCode()) {
                    baseView.searchHistorySuccess(model);
                } else {
                    baseView.searchHistoryFail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.searchHistoryFail(msg);
            }

            @Override
            public void onFinish() {

            }
        });
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
