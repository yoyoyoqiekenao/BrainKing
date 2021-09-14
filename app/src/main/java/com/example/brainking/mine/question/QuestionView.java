package com.example.brainking.mine.question;

import com.example.brainking.base.BaseView;
import com.example.brainking.model.QuestionModel;

public interface QuestionView extends BaseView {
    void makeQuestionSuccess(QuestionModel model);

    void fail(String msg);
}
