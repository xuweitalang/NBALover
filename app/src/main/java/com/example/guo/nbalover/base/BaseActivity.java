package com.example.guo.nbalover.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.guo.nbalover.Presenter.IPresenter;
import com.example.guo.nbalover.inter.IBaseView;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends IPresenter,V extends IBaseView> extends AppCompatActivity implements IBaseView {
    private static final String TAG = "BaseActivity";
    protected P mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (openEventBus()) {
            EventBus.getDefault().register(this);
        }
        initView();
        initData();
        doClick();
        doBusiness();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (openEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        if(mPresenter !=null) {
            mPresenter.onDestory(); //释放资源
        }
    }

    protected abstract boolean openEventBus();
}
