package com.example.brainking.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.brainking.MainActivity;
import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.LoginModel;
import com.example.brainking.model.VerCodeModel;
import com.example.brainking.util.SpUtils;
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
    @BindView(R.id.tv_login)
    TextView tv_login;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    private String mUuid;


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
        tv_login.setOnClickListener(this);
        tv_verCode.setOnClickListener(this);
        rl_back.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_delete) {
            ed_phone.setText("");
        } else if (view.getId() == R.id.tv_verCode) {

            if (TextUtils.isEmpty(ed_phone.getText().toString()) || ed_phone.getText().length() != 11) {
                Toast.makeText(mActivity, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "发送验证码", Toast.LENGTH_SHORT).show();
            downTimer.start();
            tv_verCode.setEnabled(false);
            basePresenter.getVerCode(ed_phone.getText().toString());
        } else if (view.getId() == R.id.tv_login) {
            basePresenter.goLogin(ed_phone.getText().toString(), ed_pwd.getText().toString(), mUuid);
        } else if (view.getId() == R.id.rl_back) {
            finish();
        }
    }

    @Override
    public void getVerCodeSuccess(VerCodeModel model) {
        mUuid = model.getUuid();
        ed_pwd.setText(model.getVerifyCode());
        Log.d("xuwudi", "uuid===" + mUuid);
    }

    @Override
    public void getVerCodeFail(String err) {

        Toast.makeText(mActivity, err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goLoginSuccess(LoginModel model) {
        SpUtils.getInstance().putString("userId", model.getData().getUserInfo().getUserId());
        SpUtils.getInstance().putString("token", model.getData().getToken());
        Log.d("xuwudi", "token===" + model.getData().getToken());
        Log.d("xuwudi", "userId===" + model.getData().getUserInfo().getUserId());
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void goLoginFail(String err) {

        Toast.makeText(mActivity, err, Toast.LENGTH_SHORT).show();
    }


}
