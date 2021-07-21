package com.example.brainking.battle.createroom;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.events.MatchStartEvent;
import com.example.brainking.model.MatchStartModel;
import com.gyf.immersionbar.ImmersionBar;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateRoomActivity extends BrainActivity<CreateRoomPresenter> implements CreateRoomView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_createRoom)
    TextView tv_createRoom;


    @Override
    protected CreateRoomPresenter createPresenter() {
        return new CreateRoomPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createroom);
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rl_back.setOnClickListener(this);
        tv_createRoom.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        } else if (v.getId() == R.id.tv_createRoom) {
            basePresenter.createRoom();
        }
    }



    @Override
    public void matchStartSuccess(MatchStartModel matchStartModel) {
        Toast.makeText(this, matchStartModel.getMsg(), Toast.LENGTH_SHORT).show();
        //MyMqttService.startService(this);
    }

    @Override
    public void fail(String err) {
        Toast.makeText(this, "创建失败" + err, Toast.LENGTH_SHORT).show();
    }


}

