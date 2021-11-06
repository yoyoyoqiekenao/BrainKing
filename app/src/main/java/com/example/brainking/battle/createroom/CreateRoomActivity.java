package com.example.brainking.battle.createroom;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.battledetail.BattleDetailActivity;
import com.example.brainking.battle.battleready.BattleReadyActivity;
import com.example.brainking.model.CreateRoomModel;
import com.example.brainking.util.SpUtils;
import com.gyf.immersionbar.ImmersionBar;


import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateRoomActivity extends BrainActivity<CreateRoomPresenter> implements CreateRoomView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.tv_createRoom)
    TextView tv_createRoom;
    @BindView(R.id.tv_level)
    TextView tv_level;
    @BindView(R.id.rl_level)
    RelativeLayout rl_level;
    @BindView(R.id.rootView)
    LinearLayout rootView;
    @BindView(R.id.ed_roomname)
    EditText ed_roomname;
    @BindView(R.id.ed_num)
    EditText ed_num;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.iv_head)
    ImageView iv_head;

    private TextView tv_level_1, tv_level_2, tv_level_3;

    private PopupWindow mPop;
    private String mLevel;


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
        rl_level.setOnClickListener(this);

        tv_name.setText(SpUtils.getInstance().getString("name"));
        Glide.with(this).load(SpUtils.getInstance().getString("headImg")).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.rl_back) {
            finish();
        } else if (v.getId() == R.id.tv_createRoom) {
            if (TextUtils.isEmpty(ed_roomname.getText().toString())) {
                Toast.makeText(this, "请输入房间名", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(ed_num.getText().toString())) {
                Toast.makeText(this, "请输入房间人数", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(mLevel)) {
                Toast.makeText(this, "请选择等级", Toast.LENGTH_SHORT).show();
                return;
            }
            if (Integer.valueOf(ed_num.getText().toString()) > 10 || Integer.valueOf(ed_num.getText().toString()) < 2) {
                Toast.makeText(this, "房间人数在2-10人", Toast.LENGTH_SHORT).show();
                return;
            }
            createPresenter().createBattleRoom(mLevel, ed_num.getText().toString(), ed_roomname.getText().toString());

        } else if (v.getId() == R.id.rl_level) {
            showLevelPop();
        } else if (v.getId() == R.id.tv_level_1) {
            mLevel = "1";
            tv_level.setText("一般");
            mPop.dismiss();
        } else if (v.getId() == R.id.tv_level_2) {
            mLevel = "2";
            tv_level.setText("中等");
            mPop.dismiss();
        } else if (v.getId() == R.id.tv_level_3) {
            mLevel = "3";
            tv_level.setText("困难");
            mPop.dismiss();
        }
    }

    private void showLevelPop() {
        View view = LayoutInflater.from(this).inflate(R.layout.pop_select_level, null);
        tv_level_1 = view.findViewById(R.id.tv_level_1);
        tv_level_2 = view.findViewById(R.id.tv_level_2);
        tv_level_3 = view.findViewById(R.id.tv_level_3);

        tv_level_1.setOnClickListener(this);
        tv_level_2.setOnClickListener(this);
        tv_level_3.setOnClickListener(this);


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
    }


    @Override
    public void matchStartSuccess(CreateRoomModel matchStartModel) {
        Intent intent = new Intent(this, BattleReadyActivity.class);
        intent.putExtra("num", ed_num.getText().toString());
        intent.putExtra("roomId", matchStartModel.getData());
        MyMqttService.startService(this, SpUtils.getInstance().getString("userId"), matchStartModel.getData());
        startActivity(intent);
        finish();
    }

    @Override
    public void fail(String err) {
        Toast.makeText(this, "创建失败" + err, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reConnect() {
        Intent intent = new Intent(this, BattleDetailActivity.class);
        intent.putExtra("type", "reConnect");
        startActivity(intent);
        finish();
    }


}

