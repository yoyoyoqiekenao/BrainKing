package com.example.brainking.mine.about;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.MyWebActivity;
import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BrainActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_1)
    RelativeLayout rl_1;
    @BindView(R.id.rl_2)
    RelativeLayout rl_2;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
    }


    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rlBack.setOnClickListener(this);
        rl_1.setOnClickListener(this);
        rl_2.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        } else if (view.getId() == R.id.rl_1) {
            Intent intent = new Intent(this, MyWebActivity.class);
            intent.putExtra("url", "http://www.wdsd66.cn/protocol/privacy_agreement.html");
            intent.putExtra("title", "隐私协议");
            startActivity(intent);
        } else if (view.getId() == R.id.rl_2) {
            Intent intent = new Intent(this, MyWebActivity.class);
            intent.putExtra("url", "http://www.wdsd66.cn/protocol/user_agreement.html");
            intent.putExtra("title", "用户协议");
            startActivity(intent);
        }
    }
}
