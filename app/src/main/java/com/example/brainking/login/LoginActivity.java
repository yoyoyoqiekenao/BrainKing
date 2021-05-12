package com.example.brainking.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.brainking.MainActivity;
import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ivDelete.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        tv_verCode.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_delete) {
            ed_phone.setText("");
        } else if (view.getId() == R.id.tv_verCode) {
            Toast.makeText(this, "发送验证码", Toast.LENGTH_SHORT).show();
        } else if (view.getId() == R.id.tv_submit) {
            startActivity(MainActivity.class);
        }
    }
}
