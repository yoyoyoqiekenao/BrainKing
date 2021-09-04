package com.example.brainking.news.newdetail;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.brainking.R;
import com.example.brainking.adapter.NewDetailAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.MessageReadModel;
import com.example.brainking.model.NewDetailModel;
import com.example.brainking.model.SendMsgModel;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewDetailActivity extends BrainActivity<NewDetailPresenter> implements NewDetailView, View.OnClickListener {


    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rv_message)
    RecyclerView rvMessage;
    @BindView(R.id.smartView)
    SmartRefreshLayout smartView;
    @BindView(R.id.ed_send)
    EditText ed_send;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;


    private int mToId;
    private String mName;

    private NewDetailAdapter mAdapter;
    private int pageNum = 1;

    private List<NewDetailModel.DataDTO> mList = new ArrayList<>();

    @Override
    protected NewDetailPresenter createPresenter() {
        return new NewDetailPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_newdetail);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        mToId = getIntent().getIntExtra("toId", 0);
        mName = getIntent().getStringExtra("name");
        tv_name.setText(mName);

        createPresenter().MessageRead(String.valueOf(mToId));

        smartView.setEnableRefresh(false);

        mAdapter = new NewDetailAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvMessage.setLayoutManager(manager);
        rvMessage.setAdapter(mAdapter);


        basePresenter.getNewDetail(1, mToId);

        ed_send.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (!TextUtils.isEmpty(ed_send.getText())) {
                        basePresenter.sendMsg(ed_send.getText().toString(), mToId);
                    } else {
                        Toast.makeText(NewDetailActivity.this, "请输入消息", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });


        smartView.setEnableLoadMore(false);
        /*smartView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull @NotNull RefreshLayout refreshLayout) {
                pageNum = pageNum + 1;
                basePresenter.getNewDetail(pageNum, mToId);
            }
        });*/

        rl_back.setOnClickListener(this);
    }

    @Override
    public void getNewDetailSuccess(NewDetailModel model) {
        //smartView.finishLoadMore();

        mList.clear();
        mList.addAll(model.getData());
        mAdapter.setList(mList);
        //rvMessage.scrollToPosition(mList.size() - 1);
    }

    @Override
    public void getNewDetailFail(String err) {

    }

    @Override
    public void sendMsgSuccess(SendMsgModel msgModel) {
        if (msgModel.isData()) {
            basePresenter.getNewDetail(1, mToId);
            ed_send.setText("");
        } else {
            Toast.makeText(NewDetailActivity.this, msgModel.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void sendMsgFail(String err) {
        Toast.makeText(NewDetailActivity.this, err, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void messageReadSuccess(MessageReadModel model) {

    }

    @Override
    public void messageReadFail(String msg) {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        }
    }
}
