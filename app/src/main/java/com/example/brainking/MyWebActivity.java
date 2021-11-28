package com.example.brainking;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWebActivity extends BrainActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.web)
    WebView web;

    private String mTitle;
    private String mUrl;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myweb);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarDarkFont(true).init();

        mTitle = getIntent().getStringExtra("title");
        mUrl = getIntent().getStringExtra("url");

        tv_title.setText(mTitle);
        web.loadUrl(mUrl);

        rl_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
        }
    }
}
