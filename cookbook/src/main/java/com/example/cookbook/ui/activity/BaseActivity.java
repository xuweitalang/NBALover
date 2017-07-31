package com.example.cookbook.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.cookbook.R;
import com.example.cookbook.utils.AppManager;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {
    private View baseView;
    protected Toolbar toolbar;
    protected TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        AppManager.getAppManager().addActivity(this);
        ButterKnife.bind(this);
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }
        initToolbar();
        initView();
        initData();
    }

    public void initToolbar() {
        toolbar = (Toolbar) baseView.findViewById(R.id.toolbar);
        if (toolbar == null) {
            return;
        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        tvTitle = (TextView) toolbar.findViewById(R.id.tv_title);
        tvTitle.setVisibility(showToolbar() ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (layoutResID == 0) {
            throw new RuntimeException("layoutResID==-1 have you create your layout?");
        }
        if (showToolbar() && getToolbarResId() != -1) {
            baseView = LayoutInflater.from(this).inflate(R.layout.activity_base, null, false); //根布局
            ViewStub vs_toolbar = (ViewStub) baseView.findViewById(R.id.vs_toolbar); //toolbar容器
            FrameLayout fl_container = (FrameLayout) baseView.findViewById(R.id.flyt_container); //子布局容器
            vs_toolbar.setLayoutResource(getToolbarResId()); //toolbar资源Id
            vs_toolbar.inflate(); //填充toolbar
            LayoutInflater.from(this).inflate(layoutResID, fl_container, true); //子布局
            setContentView(baseView);
        } else {
            //不显示通用toolbar
            super.setContentView(layoutResID);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        baseView = null;
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        AppManager.getAppManager().finishActivity(this);
    }

    //启动Activity
    protected void startActivityForName(Class<?> clazz) {
        startActivity(new Intent(this, clazz));
    }

    //启动Activity并传递对象
    protected void startActivityForSeria(Class<?> clazz, String key, Serializable obj) {
        Intent intent = new Intent(this, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, obj);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    //启动Activity传递对象并返回结果
    protected void startActivityForResult(Class<?> clazz, String key, Serializable obj, int code) {
        Intent intent = new Intent(this, clazz);
        Bundle bundle = new Bundle();
        bundle.putSerializable(key, obj);
        intent.putExtras(bundle);
        startActivityForResult(intent, code);
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract boolean showToolbar(); //是否显示通用Toolbar

    //获取自定义toolbarview 资源id 默认为-1，showToolBar()方法必须返回true才有效
    protected int getToolbarResId() {
        return R.layout.layout_common_toolbar;
    }

    ;

    protected abstract boolean openEventBus();
}
