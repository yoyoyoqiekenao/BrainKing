package com.example.brainking.mine.friend;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.MyFriendAdapter;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.mine.friendapply.FriendApplyActivity;
import com.example.brainking.model.DeleteFriendModel;
import com.example.brainking.model.FriendListModel;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.NotNull;

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
    @BindView(R.id.rootView)
    LinearLayout rootView;

    private MyFriendAdapter mAdapter;
    private PopupWindow mPop;


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
        mAdapter.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(@NonNull @NotNull BaseQuickAdapter adapter, @NonNull @NotNull View view, int position) {
                showDeletePop(model.getRows().get(position).getUserId());
                return false;
            }
        });
    }

    @Override
    public void fail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void deleteSuccess(DeleteFriendModel model) {
        createPresenter().getFriendList();
    }

    private void showDeletePop(String id) {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_delect_friend, null);
        TextView tv_cancel = view.findViewById(R.id.tv_cancel);
        TextView tv_submit = view.findViewById(R.id.tv_submit);

        mPop = new PopupWindow(this);
        mPop.setContentView(view);
        mPop.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPop.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

        // 设置PopupWindow的背景
        mPop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPop.setOutsideTouchable(true);
        mPop.setTouchable(true);
        mPop.setFocusable(true); // pop 显示时， 不让外部 view 响应点击事件

        mPop.showAtLocation(rootView, Gravity.CENTER, 0, 0);

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.dismiss();
                createPresenter().deleteFriend(id);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        createPresenter().getFriendList();
    }
}
