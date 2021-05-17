package com.example.brainking.mine.mine;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.mine.friend.FriendActivity;
import com.example.brainking.mine.record.RecordActivity;
import com.example.brainking.mine.timeteam.TimeTeamActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {

    @BindView(R.id.rl_friend)
    RelativeLayout rl_friend;
    @BindView(R.id.rl_record)
    RelativeLayout rl_record;
    @BindView(R.id.rl_timeTeam)
    RelativeLayout rl_timeTeam;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, getView());

        setOnClickListener(R.id.rl_friend);
        setOnClickListener(R.id.rl_record);
        setOnClickListener(R.id.rl_timeTeam);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_friend) {
            startActivity(FriendActivity.class);
        } else if (view.getId() == R.id.rl_record) {
            startActivity(RecordActivity.class);
        } else if (view.getId() == R.id.rl_timeTeam) {
            startActivity(TimeTeamActivity.class);
        }
    }
}
