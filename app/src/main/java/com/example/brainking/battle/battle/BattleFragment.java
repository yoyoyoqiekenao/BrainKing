package com.example.brainking.battle.battle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
import com.example.brainking.battle.joinroom.JoinRoomActivity;
import com.example.brainking.model.BattleListModel;
import com.example.brainking.model.JoinRoomModel;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
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
    @BindView(R.id.smartLayout)
    SmartRefreshLayout smartLayout;

    private BattleAdapter mAdapter;
    private String mRoomId;
    private List<JoinRoomModel.DataDTO> mList = new ArrayList<>();


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


        smartLayout.setEnableLoadMore(false);
        smartLayout.setRefreshHeader(new ClassicsHeader(getContext()));
        smartLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                smartLayout.finishRefresh(2000);
                createPresenter().getBattleList();
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

        mAdapter.setList(model.getData());


        mAdapter.addChildClickViewIds(R.id.tv_pk);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.tv_pk) {
                    mRoomId = model.getData().get(position).getRoomID();
                    createPresenter().joinRoom(model.getData().get(position).getRoomID());
                }
            }
        });
    }

    @Override
    public void Failed(String msg) {
        Toast.makeText(getContext(), msg + "", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void joinRoomSuccess(JoinRoomModel model) {
        mList.clear();
        mList.addAll(model.getData());
        Intent intent = new Intent(getContext(), JoinRoomActivity.class);
        intent.putExtra("list", (Serializable) mList);
        intent.putExtra("roomId", mRoomId);
        startActivity(intent);
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
