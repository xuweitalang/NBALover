package com.example.cookbook.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.widget.FrameLayout;

import com.example.cookbook.R;

import butterknife.Bind;

public class Homectivity extends BaseActivity {

    @Bind(R.id.home_container)
    FrameLayout homeContainer;
    @Bind(R.id.bottom_tab_layout)
    TabLayout bottomTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected boolean showToolbar() {
        return false;
    }

    @Override
    protected boolean openEventBus() {
        return false;
    }
}
