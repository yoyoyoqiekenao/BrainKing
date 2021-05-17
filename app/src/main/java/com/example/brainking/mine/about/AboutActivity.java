package com.example.brainking.mine.about;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.view)
    View mView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_about;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(getActivity()).statusBarView(mView).init();

        setOnClickListener(R.id.rl_back);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        }
    }
}
