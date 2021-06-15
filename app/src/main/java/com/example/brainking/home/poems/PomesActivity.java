package com.example.brainking.home.poems;

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

public class PomesActivity extends BrainActivity implements View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomes);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rlBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        }
    }
}
