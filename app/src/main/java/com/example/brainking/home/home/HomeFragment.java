package com.example.brainking.home.home;



import android.view.View;
import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;



import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment   {
    @BindView(R.id.view)
    View mView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this,getView());
        ImmersionBar.with(getActivity()).statusBarView(mView).init();
    }

    @Override
    protected void initData() {

    }

}
