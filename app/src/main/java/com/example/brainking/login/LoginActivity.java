package com.example.brainking.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.brainking.MainActivity;
import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.VerCodeModel;
import com.wuxiaolong.androidutils.library.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BrainActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    @Nullable
    @BindView(R.id.iv_delete)
    ImageView ivDelete;
    @BindView(R.id.tv_verCode)
    TextView tv_verCode;
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_pwd)
    EditText ed_pwd;
    @BindView(R.id.tv_submit)
    TextView tv_submit;


    private CountDownTimer downTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            tv_verCode.setText("倒计时" + millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            tv_verCode.setText("获取验证码");
            tv_verCode.setEnabled(true);
        }
    };

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    protected void initView() {
        ButterKnife.bind(this);
        ivDelete.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        tv_verCode.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_delete) {
            ed_phone.setText("");
        } else if (view.getId() == R.id.tv_verCode) {
            Toast.makeText(this, "发送验证码", Toast.LENGTH_SHORT).show();
            downTimer.start();
            tv_verCode.setEnabled(false);
            basePresenter.getVerCode(ed_phone.getText().toString());
        } else if (view.getId() == R.id.tv_submit) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    @Override
    public void getVerCodeSuccess(VerCodeModel model) {
        LogUtil.d("xuwudi",model.toString());
    }

    @Override
    public void getVerCodeFail(String err) {
        LogUtil.d("xuwudi",err);
    }
}
