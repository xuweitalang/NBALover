package com.example.guo.nbalover.Presenter;

import android.content.Context;

import com.example.guo.nbalover.IView.ISplashView;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;
import com.example.guo.nbalover.model.Respository.NBARespository;
import com.example.guo.nbalover.model.interfaces.INBARespository;

import rx.Subscriber;

/**
 * Created by guo on 2017/4/4.
 */

public class SplashPresenter extends Presenter {
    private ISplashView splashView;
    private INBARespository nbaRespository;

    public SplashPresenter(Context context, ISplashView iSplashView) {
        super(context);
        this.splashView = iSplashView;
        this.nbaRespository = NBARespository.getInstance();
    }

    public void initData() {
        rxJavaExecuter.execute(nbaRespository.getCategoryQuery(), subscriber);
    }

    private GetCategoryQuerySubscriber subscriber;

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {

    }

    private class GetCategoryQuerySubscriber extends Subscriber<TeamInfoBean> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (subscriber != null) {
                subscriber.unsubscribe();
            }
            if (splashView != null) {
                splashView.onSplashInitData();
            }
        }

        @Override
        public void onNext(TeamInfoBean teamInfoBean) {
            if (splashView != null) {
                splashView.onSplashInitData();
            }
            this.onCompleted();
        }
    }
}
