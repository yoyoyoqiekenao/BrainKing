package com.example.brainking.mine.collect;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.MyCollectAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.CollectListModel;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectActivity extends BrainActivity<CollectPresenter> implements CollectView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.rc_friend)
    RecyclerView rc_friend;

    private MyCollectAdapter mAdapter;

    @Override
    protected CollectPresenter createPresenter() {
        return new CollectPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new MyCollectAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rc_friend.setLayoutManager(manager);
        rc_friend.setAdapter(mAdapter);

        rl_back.setOnClickListener(this);

        createPresenter().getCollectList();
    }

    @Override
    public void getCollectSuccess(CollectListModel model) {
        mAdapter.setList(model.getRows());

        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

            }
        });
    }

    @Override
    public void getCollectFail(String err) {

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
}
