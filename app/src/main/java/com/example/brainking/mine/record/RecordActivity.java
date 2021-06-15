package com.example.brainking.mine.record;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordActivity extends BrainActivity implements View.OnClickListener {
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.view)
    View mView;


    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        initView();
    }



     protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        }
    }
}
