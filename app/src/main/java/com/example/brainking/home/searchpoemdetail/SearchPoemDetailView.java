package com.example.brainking.home.searchpoemdetail;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.SearchPoemDetailModel;

public interface SearchPoemDetailView extends BaseView {
    void getSearchPoemDetailSuccess(SearchPoemDetailModel model);

    void getSearchPoemDetailFail(String msg);
}
