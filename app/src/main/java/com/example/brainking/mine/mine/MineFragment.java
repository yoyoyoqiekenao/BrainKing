package com.example.brainking.mine.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.home.userinfo.UserInfoActivity;
import com.example.brainking.login.LoginActivity;
import com.example.brainking.mine.about.AboutActivity;
import com.example.brainking.mine.collect.CollectActivity;
import com.example.brainking.mine.friend.FriendActivity;
import com.example.brainking.mine.playabout.PlayAboutActivity;
import com.example.brainking.mine.question.QuestionActivity;
import com.example.brainking.mine.record.RecordActivity;
import com.example.brainking.mine.timeteam.TimeTeamActivity;
import com.example.brainking.model.LoginOutModel;
import com.example.brainking.model.UserInfoModel;
import com.example.brainking.util.SpUtils;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BrainFragment<MinePresenter> implements MineView, View.OnClickListener {

    @BindView(R.id.rl_friend)
    RelativeLayout rl_friend;
    @BindView(R.id.rl_record)
    RelativeLayout rl_record;
    @BindView(R.id.rl_timeTeam)
    RelativeLayout rl_timeTeam;
    @BindView(R.id.rl_about)
    RelativeLayout rl_about;
    @BindView(R.id.rl_play_about)
    RelativeLayout rl_play_about;
    @BindView(R.id.iv_head)
    ImageView iv_head;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_remark)
    TextView tv_remark;
    @BindView(R.id.rl_collect)
    RelativeLayout rl_collect;
    @BindView(R.id.rl_loginOut)
    RelativeLayout rl_loginOut;
    @BindView(R.id.rl_question)
    RelativeLayout rl_question;
    @BindView(R.id.iv_edit)
    ImageView iv_edit;
    @BindView(R.id.iv_level)
    ImageView iv_level;
    @BindView(R.id.view_fighting)
    View view_fighting;
    @BindView(R.id.tv_fighting)
    TextView tv_fighting;
    @BindView(R.id.rl_loginOff)
    RelativeLayout rl_loginOff;



    private String mImg;
    private String mName;
    private String mRemark;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.fragment_mine, null);
        initView(view);
        return view;
    }


    protected void initView(View view) {
        ButterKnife.bind(this, view);


        rl_friend.setOnClickListener(this);
        rl_record.setOnClickListener(this);
        rl_timeTeam.setOnClickListener(this);
        rl_about.setOnClickListener(this);
        rl_play_about.setOnClickListener(this);
        rl_collect.setOnClickListener(this);
        rl_loginOut.setOnClickListener(this);
        rl_question.setOnClickListener(this);
        iv_edit.setOnClickListener(this);
        rl_loginOff.setOnClickListener(this);

        createPresenter().getUserInfo();
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_friend) {
            startActivity(new Intent(getContext(), FriendActivity.class));
        } else if (view.getId() == R.id.rl_record) {
            startActivity(new Intent(getContext(), RecordActivity.class));
        } else if (view.getId() == R.id.rl_timeTeam) {
            startActivity(new Intent(getContext(), TimeTeamActivity.class));
        } else if (view.getId() == R.id.rl_about) {
            startActivity(new Intent(getContext(), AboutActivity.class));
        } else if (view.getId() == R.id.rl_play_about) {
            startActivity(new Intent(getContext(), PlayAboutActivity.class));
        } else if (view.getId() == R.id.rl_collect) {
            startActivity(new Intent(getContext(), CollectActivity.class));
        } else if (view.getId() == R.id.rl_loginOut) {
            createPresenter().loginOut();
        } else if (view.getId() == R.id.rl_question) {
            startActivity(new Intent(getContext(), QuestionActivity.class));
        } else if (view.getId() == R.id.iv_edit) {
            Intent intent = new Intent(getContext(), UserInfoActivity.class);
            intent.putExtra("img", mImg);
            intent.putExtra("name", mName);
            intent.putExtra("remark", mRemark);
            startActivity(intent);
        }else if(view.getId()==R.id.rl_loginOff){
            createPresenter().loginOff();
        }
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter(this);
    }

    @Override
    public void getUserInfoSuccess(UserInfoModel model) {
        Glide.with(getContext()).load(model.getData().getAvatar()).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(iv_head);
        tv_name.setText(model.getData().getNickName());
        tv_remark.setText(model.getData().getRemark());

        mImg = model.getData().getAvatar();
        mName = model.getData().getNickName();
        mRemark = model.getData().getRemark();
        tv_fighting.setText(model.getData().getFightinga()+"/"+model.getData().getFightingb());
        view_fighting.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, (float) div(Double.valueOf(model.getData().getFightinga()), Double.valueOf(model.getData().getFightingb()), 2)));
    }

    @Override
    public void getUserInfoFail(String msg) {

    }

    @Override
    public void loginOutSuccess(LoginOutModel model) {
        SpUtils.getInstance().clear();
        MyMqttService.stopService(getContext());
        startActivity(new Intent(getContext(), LoginActivity.class));
        getActivity().finish();
    }

    @Override
    public void loginOutFail(String msg) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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
