package com.example.brainking.mine.record;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordActivity extends BaseActivity {
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.view)
    View mView;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_record;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

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
