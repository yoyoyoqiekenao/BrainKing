package com.example.brainking.popupwindows;

import android.content.Context;
import android.view.View;

import com.example.brainking.R;

import razerdp.basepopup.BasePopupWindow;

public class SelectLevelPop extends BasePopupWindow {
    public SelectLevelPop(Context context) {
        super(context);
        setContentView(R.layout.pop_select_level);
    }

    @Override
    public void setContentView(View view) {

    }
}
