package com.example.brainking.mine.friend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.R;
import com.example.brainking.adapter.MyFriendAdapter;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.mine.friendapply.FriendApplyActivity;
import com.example.brainking.model.FriendListModel;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 好友列表
 */
public class FriendActivity extends BrainActivity<FriendPresenter> implements FriendView, View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rc_friend)
    RecyclerView rc_friend;
    @BindView(R.id.tv_apply)
    TextView tv_apply;

    private MyFriendAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        initView();
    }

    @Override
    protected FriendPresenter createPresenter() {
        return new FriendPresenter(this);
    }


    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mRlBack.setOnClickListener(this);

        mAdapter = new MyFriendAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_friend.setLayoutManager(manager);
        rc_friend.setAdapter(mAdapter);


        tv_apply.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        } else if (view.getId() == R.id.tv_apply) {
            startActivity(new Intent(this, FriendApplyActivity.class));
        }
    }

    @Override
    public void getFriendListSuccess(FriendListModel model) {
        mAdapter.setList(model.getRows());
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        createPresenter().getFriendList();
    }
}
