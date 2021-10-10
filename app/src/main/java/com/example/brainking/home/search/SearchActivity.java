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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.brainking.R;
import com.example.brainking.adapter.SearchAdapter;
import com.example.brainking.adapter.SearchHistoryAdapter;
import com.example.brainking.base.BrainActivity;
import com.example.brainking.home.searchpoemdetail.SearchPoemDetailActivity;
import com.example.brainking.model.SearchHistoryModel;
import com.example.brainking.model.SearchModel;
import com.example.brainking.util.SpUtils;
import com.gyf.immersionbar.ImmersionBar;

import org.jetbrains.annotations.NotNull;

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
    @BindView(R.id.rv_history)
    RecyclerView rv_history;
    @BindView(R.id.tv_clear)
    TextView tv_clear;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;
    @BindView(R.id.ll_empty)
    LinearLayout ll_empty;


    private SearchAdapter mAdapter;
    private SearchHistoryAdapter mHistoryAdapter;
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

        mHistoryAdapter = new SearchHistoryAdapter();
        StaggeredGridLayoutManager manager_history = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        rv_history.setLayoutManager(manager_history);
        rv_history.setAdapter(mHistoryAdapter);


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

        createPresenter().searchHistory(SpUtils.getInstance().getString("userId"));

        tv_clear.setOnClickListener(this);
    }


    @Override
    public void searchSuccess(SearchModel model) {
        ll_history.setVisibility(View.GONE);


        if (model.getData() != null && model.getData().size() > 0) {
            ll_empty.setVisibility(View.GONE);
            rvSearch.setVisibility(View.VISIBLE);
        } else {
            ll_empty.setVisibility(View.VISIBLE);
            rvSearch.setVisibility(View.GONE);
        }

        mAdapter.setList(model.getData());
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
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
    public void searchHistorySuccess(SearchHistoryModel model) {
        if(model.getRows()!=null&&model.getRows().size()>0){
            ll_history.setVisibility(View.VISIBLE);
            mHistoryAdapter.setList(model.getRows());

            mHistoryAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                    Intent intent = new Intent(SearchActivity.this, SearchPoemDetailActivity.class);
                    intent.putExtra("id", model.getRows().get(position).getId());
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    public void searchHistoryFail(String err) {

    }

    @Override
    public void deleteSuccess() {
        ll_history.setVisibility(View.GONE);
    }

    @Override
    public void deleteFail(String msg) {
        ll_history.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_cancel) {
            finish();
        } else if (v.getId() == R.id.tv_clear) {
            createPresenter().delAllHistory();
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
