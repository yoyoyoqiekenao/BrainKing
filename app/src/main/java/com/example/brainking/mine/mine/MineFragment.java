package com.example.brainking.mine.mine;

import android.view.View;
import android.widget.RelativeLayout;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.mine.friend.FriendActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MineFragment extends BaseFragment {

    @BindView(R.id.rl_friend)
    RelativeLayout rl_friend;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this, getView());

        setOnClickListener(R.id.rl_friend);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.rl_friend){
            startActivity(FriendActivity.class);
        }
    }
}
