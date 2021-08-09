package com.example.brainking.battle.battle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.BattleAdapter;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.battle.friend_pk.FriendPkActivity;
import com.example.brainking.model.BattleListModel;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;


//对战大厅
public class BattleFragment extends BrainFragment<BattlePresenter> implements BattleView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rc_battle)
    RecyclerView rc_battle;
    @BindView(R.id.iv_create)
    ImageView iv_create;

    private BattleAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_battle, null);

        initView(view);
        return view;
    }


    protected void initView(View view) {
        ButterKnife.bind(this, view);
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new BattleAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_battle.setLayoutManager(manager);
        rc_battle.setAdapter(mAdapter);

        iv_create.setOnClickListener(this);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_pk) {

                }
            }
        });


    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected BattlePresenter createPresenter() {
        return new BattlePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        createPresenter().getBattleList();
    }

    @Override
    public void getBattleListSuccess(BattleListModel model) {
        mAdapter.setNewData(model.getData());
    }

    @Override
    public void Failed(String msg) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_create:
                Intent intent = new Intent(mActivity, FriendPkActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
