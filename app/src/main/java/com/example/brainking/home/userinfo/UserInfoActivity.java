package com.example.brainking.home.userinfo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.UpdateUserInfoModel;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends BrainActivity<UserInfoPresenter> implements UserInfoView, View.OnClickListener {

    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_code)
    EditText ed_code;
    @BindView(R.id.iv_close)
    ImageView iv_close;
    @BindView(R.id.iv_submit)
    ImageView iv_submit;

    private String mImg;
    private String mName;
    private String mRemark;

    @Override
    protected UserInfoPresenter createPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        mImg = getIntent().getStringExtra("img");
        mName = getIntent().getStringExtra("name");
        mRemark = getIntent().getStringExtra("remark");
        Glide.with(this).load(mImg).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);

        if (!TextUtils.isEmpty(mName)) {
            ed_name.setText(mName);
        }

        rl_back.setOnClickListener(this);
        iv_close.setOnClickListener(this);
        iv_submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.iv_close:
                ed_name.setText("");
                break;
            case R.id.iv_submit:
                if (TextUtils.isEmpty(ed_name.getText().toString())) {
                    Toast.makeText(UserInfoActivity.this, "请输入昵称", Toast.LENGTH_SHORT).show();
                    return;
                }
                createPresenter().updateUserInfo(ed_name.getText().toString(), ed_code.getText().toString(), mImg, mRemark);
                break;
            default:
        }
    }

    @Override
    public void updateSuccess(UpdateUserInfoModel model) {
        finish();
    }

    @Override
    public void fail(String err) {

    }
}
