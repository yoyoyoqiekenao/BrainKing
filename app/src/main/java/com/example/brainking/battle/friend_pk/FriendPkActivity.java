package com.example.brainking.battle.friend_pk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.createroom.CreateRoomActivity;
import com.example.brainking.dispatcher.AppResponseDispatcher;
import com.example.brainking.util.SpUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendPkActivity extends BrainActivity implements View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_create)
    ImageView iv_create;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;

    private WebSocketSetting mSetting;
    private WebSocketManager mManager;

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendpk);
        initView();
    }

    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        iv_create.setOnClickListener(this);
        rl_back.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_create) {
            startActivity(new Intent(this, CreateRoomActivity.class));
        } else if (view.getId() == R.id.rl_back) {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManager != null) {
            mManager.destroy();
        }
    }
}