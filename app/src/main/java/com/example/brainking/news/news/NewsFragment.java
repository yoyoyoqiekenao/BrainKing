package com.example.brainking.news.news;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.MessageListAdapter;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.model.MessageListModel;
import com.example.brainking.model.NewDetailModel;

import com.example.brainking.news.newdetail.NewDetailActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends BrainFragment<NewsPresenter> implements NewsView {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.smartView)
    SmartRefreshLayout smartView;


    private MessageListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_news, null);
        initView(view);
        return view;
    }

    protected void initView(View view) {
        ButterKnife.bind(this, view);
        ImmersionBar.with(this).statusBarView(mView).init();

        mAdapter = new MessageListAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        rvMessage.setLayoutManager(manager);
        rvMessage.setAdapter(mAdapter);

        ClassicsHeader classicsHeader = new ClassicsHeader(getContext());
        smartView.setRefreshHeader(classicsHeader);

        smartView.setEnableLoadMore(false);
        smartView.setEnableRefresh(true);
        smartView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                createPresenter().getMessageList(1);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        createPresenter().getMessageList(1);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    protected NewsPresenter createPresenter() {
        return new NewsPresenter(this);
    }

    @Override
    public void getMessageListSuccess(MessageListModel messageListModel) {
        smartView.finishRefresh();
        mAdapter.setList(messageListModel.getData());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), NewDetailActivity.class);
                intent.putExtra("toId", messageListModel.getData().get(position).getUserId());
                intent.putExtra("name", messageListModel.getData().get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void getMessageListFail(String err) {
        Toast.makeText(getContext(), err, Toast.LENGTH_SHORT).show();
    }
}
