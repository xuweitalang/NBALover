package com.example.cookbook.presenter;

import android.content.Context;

import com.example.cookbook.IView.ICookSearchView;
import com.example.cookbook.model.entity.AllCookBean;
import com.example.cookbook.model.respository.CookRespository;
import com.example.cookbook.model.respository.ICookRespository;

import java.util.ArrayList;

import rx.Subscriber;

/**
 * Created by xuwei on 2017/7/28.
 */

public class CookSearchPresenter extends Presenter {
    private ICookRespository respository;
    private ICookSearchView searchView;

    public CookSearchPresenter(Context context, ICookSearchView searchView) {
        super(context);
        this.respository = CookRespository.getInstance();
        this.searchView = searchView;
    }

    @Override
    protected void destory() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    public void searchCook(String menu, String size) {
        searchView.showLoading();
        executor.execute(respository.getCategoryQuery(menu, size), subscriber = new CookSearchSubscriber());
    }

    private CookSearchSubscriber subscriber;

    private class CookSearchSubscriber extends Subscriber<AllCookBean> {

        @Override
        public void onCompleted() {
            if (searchView != null) {
                searchView.hideLoading();
            }
        }

        @Override
        public void onError(Throwable e) {
            if (subscriber != null) {
                subscriber.unsubscribe();
            }
            if (searchView != null) {
                searchView.hideLoading();
            }
        }

        @Override
        public void onNext(AllCookBean allCookBean) {
            if (allCookBean == null || allCookBean.getResult() == null) {
                if (searchView != null) {
                    searchView.cookSearchError("找不到相关菜谱");
                    this.onCompleted();
                    return;
                }
            }
            if (searchView != null) {
                if (allCookBean.getResult().getData().size() == 0) {
                    searchView.cookSearchEmpty();
                } else {
                    searchView.cookSearchSuccess((ArrayList<AllCookBean.ResultBean.DataBean>) allCookBean.getResult().getData());
                }
            }
            this.onCompleted();
        }
    }
}
