package com.example.brainking.home.search;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.SearchHistoryModel;
import com.example.brainking.model.SearchModel;

public interface SearchView extends BaseView {

    void searchSuccess(SearchModel model);

    void searchFail(String err);

    void searchHistorySuccess(SearchHistoryModel model);

    void searchHistoryFail(String err);

    void deleteSuccess();

    void deleteFail(String msg);
}
