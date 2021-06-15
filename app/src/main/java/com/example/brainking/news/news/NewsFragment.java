package com.example.brainking.news.news;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.brainking.R;
import com.example.brainking.base.BaseFragment;
import com.example.brainking.base.BasePresenter;
import com.example.brainking.base.BrainFragment;

public class NewsFragment extends BrainFragment {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LayoutInflater.from(mActivity).inflate(R.layout.fragment_news,null);
        initView(view);
        return view;


    }

     protected void initView(View view) {

    }




}
