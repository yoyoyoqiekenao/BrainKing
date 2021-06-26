package com.example.brainking.home.poems;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.brainking.R;
import com.example.brainking.adapter.LearnListAdapter_poems;
import com.example.brainking.base.BaseActivity;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.model.LearnListModel;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PoemsActivity extends BrainActivity<PoemsPresenter> implements PoemsView, View.OnClickListener {

    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rc_poems)
    RecyclerView rcPoems;


    private LearnListAdapter_poems mAdapter_poems;

    @Override
    protected PoemsPresenter createPresenter() {
        return new PoemsPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomes);

        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();

        rlBack.setOnClickListener(this);

        basePresenter.getLearnList_poems();


        mAdapter_poems.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (view.getId() == R.id.rl) {

                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.rl_back) {
            finish();
        }
    }

    @Override
    public void getLearnListSuccess(LearnListModel model) {
        mAdapter_poems = new LearnListAdapter_poems();
        mAdapter_poems.setNewData(model.getData());

        GridLayoutManager manager = new GridLayoutManager(this, 3);
        rcPoems.setLayoutManager(manager);
        rcPoems.setAdapter(mAdapter_poems);
    }

    @Override
    public void getLearnListFail(String msg) {

    }
}
