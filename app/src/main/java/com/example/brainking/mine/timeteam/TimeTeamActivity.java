package com.example.brainking.mine.timeteam;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.R;
import com.example.brainking.adapter.TimeTeamAdapter;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TimeTeamActivity extends BrainActivity implements View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rc_team)
    RecyclerView rc_team;

    private TimeTeamAdapter mAdapter;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeteam);
        initView();
    }



    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new TimeTeamAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_team.setLayoutManager(manager);
        rc_team.setAdapter(mAdapter);
        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mAdapter.setNewData(mList);

        mRlBack.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

    }
}
