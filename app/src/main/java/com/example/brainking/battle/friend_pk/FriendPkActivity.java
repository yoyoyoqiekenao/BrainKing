package com.example.brainking.battle.friend_pk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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
        /*mSetting = new WebSocketSetting();
        mSetting.setConnectUrl("ws://42.192.234.149:8080/websocket/" + SpUtils.getInstance().getString("userId"));
        //设置连接超时时间
        mSetting.setConnectTimeout(10 * 1000);
        //设置心跳间隔时间
        mSetting.setConnectionLostTimeout(60);
        //设置断开后的重连次数，可以设置的很大，不会有什么性能上的影响
        mSetting.setReconnectFrequency(40);
        //设置 Headers
        //mSetting.setHttpHeaders(header);
        //设置消息分发器，接收到数据后先进入该类中处理，处理完再发送到下游
        mSetting.setResponseProcessDispatcher(new AppResponseDispatcher());
        //接收到数据后是否放入子线程处理，只有设置了 ResponseProcessDispatcher 才有意义
        mSetting.setProcessDataOnBackground(true);
        //网络状态发生变化后是否重连，
        //需要调用 WebSocketHandler.registerNetworkChangedReceiver(context) 方法注册网络监听广播
        mSetting.setReconnectWithNetworkChanged(true);

        //通过 init 方法初始化默认的 WebSocketManager 对象
        mManager= WebSocketHandler.init(mSetting);
        //启动连接
        mManager.start();*/


    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_create) {
            startActivity(new Intent(this, CreateRoomActivity.class));
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