package com.example.cookbook.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if(openEventBus()) {
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if(openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected abstract int getLayoutId();
    protected abstract void initData();
    protected abstract void initView();
    protected abstract boolean openEventBus();
}
