package com.example.brainking.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

public class ExamplePagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mList;

    public ExamplePagerAdapter(@NonNull FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.mList = list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
