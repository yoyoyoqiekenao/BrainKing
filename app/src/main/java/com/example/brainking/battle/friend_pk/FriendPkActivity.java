package com.example.brainking.battle.friend_pk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.R;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.battle.createroom.CreateRoomActivity;
import com.example.brainking.dispatcher.AppResponseDispatcher;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.util.SpUtils;
import com.gyf.immersionbar.ImmersionBar;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendPkActivity extends BrainActivity<FriendPkPresenter> implements FriendPkView, View.OnClickListener {
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.iv_create)
    ImageView iv_create;
    @BindView(R.id.rl_back)
    RelativeLayout rl_back;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    @BindView(R.id.tv_fighting)
    TextView tv_fighting;
    @BindView(R.id.view_fighting)
    View view_fighting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friendpk);
        initView();
    }

    @Override
    protected FriendPkPresenter createPresenter() {
        return new FriendPkPresenter(this);
    }

    protected void initView() {
        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        iv_create.setOnClickListener(this);
        rl_back.setOnClickListener(this);

        tv_1.setText(setColor_1(this,"点击“发起挑战”按钮,你可以开房间并向你的好友发起挑战。"));;
        tv_2.setText(setColor_2(this,"当然,你也可以通过点击“加入房间”进入到好友创建的房间,仅需输入房间号即可。"));
        createPresenter().getUserInfo();


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

    }

    @Override
    public void getUserInfoSuccess(UserInfoModel model) {
        Glide.with(this).load(model.getData().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        tv_name.setText(model.getData().getNickName());

        tv_fighting.setText(model.getData().getFightinga() + "/" + model.getData().getFightingb());
        view_fighting.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) div(Double.valueOf(model.getData().getFightinga()), Double.valueOf(model.getData().getFightingb()), 2)));
    }

    @Override
    public void getUserInfoFail(String msg) {

    }

    public static CharSequence setColor_1(Context context, String text) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_F2CB51)), 2, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }
    public static CharSequence setColor_2(Context context, String text) {
        SpannableStringBuilder style = new SpannableStringBuilder(text);
        style.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.color_F2CB51)), 11, 17, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return style;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue() * 10;
    }
}