package com.example.brainking.home.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.brainking.R;
import com.example.brainking.adapter.SearchAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.home.searchpoemdetail.SearchPoemDetailActivity;
import com.example.brainking.model.SearchModel;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BrainActivity<SearchPresenter> implements SearchView, View.OnClickListener {

    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.rv_search)
    RecyclerView rvSearch;
    @BindView(R.id.ed_search)
    EditText edSearch;


    private SearchAdapter mAdapter;
    private Handler mHandler = new Handler();


    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        ButterKnife.bind(this);
        ImmersionBar.with(this).statusBarView(mView).init();
        openKeyboard(edSearch);
        tvCancel.setOnClickListener(this);

        mAdapter = new SearchAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        rvSearch.setLayoutManager(manager);
        rvSearch.setAdapter(mAdapter);

        edSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    closeKeyboard();
                    basePresenter.search(edSearch.getText().toString(), 1);
                }
                return false;
            }
        });


        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openKeyboard(edSearch);
            }
        }, 500);
    }


    @Override
    public void searchSuccess(SearchModel model) {
        rvSearch.setVisibility(View.VISIBLE);

        mAdapter.setNewData(model.getData());
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(SearchActivity.this, SearchPoemDetailActivity.class);
                intent.putExtra("id", model.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    public void searchFail(String err) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHandler!=null){
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            finish();
        }
    }

    private void closeKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void openKeyboard(EditText editText) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);

    }
}
