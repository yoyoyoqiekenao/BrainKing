package com.example.brainking.mine.friendapply;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.FriendApplyAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.FriendListModel;
import com.example.brainking.model.Friend_Pass_refuse_Model;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendApplyActivity extends BrainActivity<FriendApplyPresenter> implements FriendApplyView, View.OnClickListener {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout mRlBack;
    @BindView(R.id.rc_friendApply)
    RecyclerView rc_friendApply;

    private FriendApplyAdapter mAdapter;

    @Override
    protected FriendApplyPresenter createPresenter() {
        return new FriendApplyPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendapply);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new FriendApplyAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_friendApply.setLayoutManager(manager);
        rc_friendApply.setAdapter(mAdapter);

        mRlBack.setOnClickListener(this);

        createPresenter().getFriendApplyList();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_back:
                finish();
                break;
            default:
        }
    }

    @Override
    public void getFriendListSuccess(FriendListModel model) {
        mAdapter.setList(model.getRows());

        mAdapter.addChildClickViewIds(R.id.tv_agree);
        mAdapter.addChildClickViewIds(R.id.tv_refuse);
        mAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_agree:
                        createPresenter().PassApply(model.getRows().get(position).getUserId());
                        break;
                    case R.id.tv_refuse:
                        createPresenter().PassRefuse(model.getRows().get(position).getUserId());
                        break;
                    default:
                }
            }
        });
    }

    @Override
    public void fail(String msg) {

    }

    @Override
    public void passAdd(Friend_Pass_refuse_Model model) {
        createPresenter().getFriendApplyList();
    }

    @Override
    public void passRefuse(Friend_Pass_refuse_Model model) {
        createPresenter().getFriendApplyList();
    }
}
