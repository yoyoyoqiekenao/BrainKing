package com.example.brainking.base;

import com.example.brainking.net.ResponseBean;

public interface BaseView<T> {

    void showLoading();

    void hideLoading();

    void onErrorCode(ResponseBean bean);

}
