package com.example.brainking.mine.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.brainking.MyMqttService;
import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
}
