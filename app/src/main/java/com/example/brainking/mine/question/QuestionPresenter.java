package com.example.brainking.mine.question;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.json.QuestionJson;
import com.example.brainking.model.QuestionModel;
import com.example.brainking.net.ApiCallback;

import java.util.ArrayList;
import java.util.List;

public class QuestionPresenter extends BasePresenter<QuestionView> {
    public QuestionPresenter(QuestionView view) {
        attachView(view);
    }

    public void MakeQuestion(String content, String a, String b, String c, String d, int type) {
        baseView.showLoading();
        QuestionJson json = new QuestionJson();
        List<String> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        json.setList(list);
        json.setTitle(content);
        json.setQuType(type);

        addSubscription(apiStores.putQuestion(json), new ApiCallback<QuestionModel>() {

            @Override
            public void onSuccess(QuestionModel model) {
                if (model.getCode() == 200) {
                    baseView.makeQuestionSuccess(model);
                } else {
                    baseView.fail(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                baseView.fail(msg);
            }

            @Override
            public void onFinish() {
                baseView.hideLoading();
            }
        });
    }
}
