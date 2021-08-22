package com.example.brainking.mine.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;
import com.example.brainking.mine.about.AboutActivity;
import com.example.brainking.mine.friend.FriendActivity;
import com.example.brainking.mine.playabout.PlayAboutActivity;
import com.example.brainking.mine.record.RecordActivity;
import com.example.brainking.mine.timeteam.TimeTeamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class MineFragment extends BrainFragment implements View.OnClickListener {

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

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


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
        }
    }
}
