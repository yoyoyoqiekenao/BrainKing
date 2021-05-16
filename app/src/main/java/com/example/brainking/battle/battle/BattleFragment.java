package com.example.brainking.battle.battle;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleAdapter;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.battle.friend_pk.FriendPkActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//对战大厅
public class BattleFragment extends BaseFragment {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rc_battle)
    RecyclerView rc_battle;

    private BattleAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_battle;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, getView());
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new BattleAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_battle.setLayoutManager(manager);
        rc_battle.setAdapter(mAdapter);

        List<String> mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mAdapter.setNewData(mList);

        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_pk) {
                    startActivity(FriendPkActivity.class);
                }
            }
        });
    }

    @Override
    protected void initData() {

    }
}
