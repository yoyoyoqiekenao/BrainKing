package com.example.brainking.mine.question;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.QuestionModel;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends BrainActivity<QuestionPresenter> implements QuestionView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.ed_content)
    EditText ed_content;
    @BindView(R.id.ed_a)
    EditText ed_a;
    @BindView(R.id.ed_b)
    EditText ed_b;
    @BindView(R.id.ed_c)
    EditText ed_c;
    @BindView(R.id.ed_d)
    EditText ed_d;
    @BindView(R.id.tv_submit)
    TextView tv_submit;

    @Override
    protected QuestionPresenter createPresenter() {
        return new QuestionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_submit:
                if (TextUtils.isEmpty(ed_content.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "请输入内容", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ed_a.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "请输入选项A", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ed_b.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "请输入选项B", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ed_c.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "请输入选项C", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(ed_d.getText().toString())) {
                    Toast.makeText(QuestionActivity.this, "请输入选项D", Toast.LENGTH_SHORT).show();
                    return;
                }
                createPresenter().MakeQuestion(ed_content.getText().toString(), ed_a.getText().toString()
                        , ed_b.getText().toString(), ed_c.getText().toString(), ed_d.getText().toString(), 0);
                break;
            default:
        }
    }

    @Override
    public void makeQuestionSuccess(QuestionModel model) {
        Toast.makeText(QuestionActivity.this, "出题成功", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(QuestionActivity.this, "出题失败" + msg, Toast.LENGTH_SHORT).show();
    }
}
