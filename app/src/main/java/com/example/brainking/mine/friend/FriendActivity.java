package com.example.brainking.mine.friend;

import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.R;
import com.example.brainking.adapter.MyFriendAdapter;
import com.example.brainking.base.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 好友列表
 */
public class FriendActivity extends BaseActivity {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rc_friend)
    RecyclerView rc_friend;

    private MyFriendAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(getActivity()).statusBarView(mView).init();

        setOnClickListener(R.id.rl_back);

        mAdapter = new MyFriendAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_friend.setLayoutManager(manager);
        rc_friend.setAdapter(mAdapter);

        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");

        mAdapter.setNewData(mList);

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
