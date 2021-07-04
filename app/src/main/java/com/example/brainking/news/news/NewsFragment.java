package com.example.brainking.news.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.R;
import com.example.brainking.adapter.MessageListAdapter;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.model.MessageListModel;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFragment extends BrainFragment<NewsPresenter> implements NewsView {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;


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
        mAdapter.setNewData(messageListModel.getData());
        Log.d("xuwudi", "请求成功===" + messageListModel.toString());
    }

    @Override
    public void getMessageListFail(String err) {
        Log.d("xuwudi", "err===" + err);
    }
}
