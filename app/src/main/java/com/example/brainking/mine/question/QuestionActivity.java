package com.example.brainking.mine.question;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
    @BindView(R.id.iv_1)
    ImageView iv_1;
    @BindView(R.id.iv_2)
    ImageView iv_2;
    @BindView(R.id.iv_3)
    ImageView iv_3;
    @BindView(R.id.iv_4)
    ImageView iv_4;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_3)
    TextView tv_3;
    @BindView(R.id.tv_4)
    TextView tv_4;

    private String mRight;
    private String mType;

    @Override
    protected QuestionPresenter createPresenter() {
        return new QuestionPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_question);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mType = "1";

        rl_back.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        iv_1.setOnClickListener(this);
        iv_2.setOnClickListener(this);
        iv_3.setOnClickListener(this);
        iv_4.setOnClickListener(this);
        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_1:
                mType = "1";
                tv_1.setBackgroundResource(R.color.color_FF637F);
                tv_2.setBackgroundResource(R.color.color_ffffff);
                tv_3.setBackgroundResource(R.color.color_ffffff);
                tv_4.setBackgroundResource(R.color.color_ffffff);
                break;
            case R.id.tv_2:
                mType = "2";
                tv_1.setBackgroundResource(R.color.color_ffffff);
                tv_2.setBackgroundResource(R.color.color_FF637F);
                tv_3.setBackgroundResource(R.color.color_ffffff);
                tv_4.setBackgroundResource(R.color.color_ffffff);
                break;
            case R.id.tv_3:
                mType = "3";
                tv_1.setBackgroundResource(R.color.color_ffffff);
                tv_2.setBackgroundResource(R.color.color_ffffff);
                tv_3.setBackgroundResource(R.color.color_FF637F);
                tv_4.setBackgroundResource(R.color.color_ffffff);
                break;
            case R.id.tv_4:
                mType = "4";
                tv_1.setBackgroundResource(R.color.color_ffffff);
                tv_2.setBackgroundResource(R.color.color_ffffff);
                tv_3.setBackgroundResource(R.color.color_ffffff);
                tv_4.setBackgroundResource(R.color.color_FF637F);
                break;
            case R.id.iv_1:
                mRight = "1";
                iv_1.setImageResource(R.mipmap.iv_question_select);
                iv_2.setImageResource(R.mipmap.iv_question_unselect);
                iv_3.setImageResource(R.mipmap.iv_question_unselect);
                iv_4.setImageResource(R.mipmap.iv_question_unselect);
                break;
            case R.id.iv_2:
                mRight = "2";
                iv_1.setImageResource(R.mipmap.iv_question_unselect);
                iv_2.setImageResource(R.mipmap.iv_question_select);
                iv_3.setImageResource(R.mipmap.iv_question_unselect);
                iv_4.setImageResource(R.mipmap.iv_question_unselect);
                break;
            case R.id.iv_3:
                mRight = "3";
                iv_1.setImageResource(R.mipmap.iv_question_unselect);
                iv_2.setImageResource(R.mipmap.iv_question_unselect);
                iv_3.setImageResource(R.mipmap.iv_question_select);
                iv_4.setImageResource(R.mipmap.iv_question_unselect);
                break;
            case R.id.iv_4:
                mRight = "4";
                iv_1.setImageResource(R.mipmap.iv_question_unselect);
                iv_2.setImageResource(R.mipmap.iv_question_unselect);
                iv_3.setImageResource(R.mipmap.iv_question_unselect);
                iv_4.setImageResource(R.mipmap.iv_question_select);
                break;
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
                if (TextUtils.isEmpty(mRight)) {
                    Toast.makeText(QuestionActivity.this, "请选择正确答案", Toast.LENGTH_SHORT).show();
                    return;
                }

                createPresenter().MakeQuestion(ed_content.getText().toString(), ed_a.getText().toString()
                        , ed_b.getText().toString(), ed_c.getText().toString(), ed_d.getText().toString(), mType, mRight);
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
