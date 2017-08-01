package com.example.guo.nbalover.base;

import com.example.guo.nbalover.Presenter.IPresenter;
import com.example.guo.nbalover.inter.IBaseModel;
import com.example.guo.nbalover.inter.IBaseView;

/**
 * Created by xuwei on 2017/7/8.
 */

public class BasePresenter<M extends IBaseModel,V extends IBaseView> implements IPresenter {
    private M baseModel;
    private V baseView;

    public BasePresenter(M baseModel, V baseView) {
        this.baseModel = baseModel;
        this.baseView = baseView;
        onStart();
    }

    public BasePresenter(M baseModel) {
        this.baseModel = baseModel;
        onStart();
    }

    public BasePresenter(V baseView) {
        this.baseView = baseView;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {
        if(baseModel!=null) {
            baseModel.onDestroy();
        }
        baseModel = null;
        baseView = null;
    }
}
