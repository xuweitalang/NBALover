package com.example.guo.nbalover.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.guo.nbalover.IView.IAllTeamView;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;
import com.example.guo.nbalover.model.Respository.NBARespository;
import com.example.guo.nbalover.model.interfaces.INBARespository;
import com.example.guo.nbalover.utils.ErrorExceptionUtil;

import rx.Subscriber;

/**
 * Created by guo on 2017/4/4.
 */

public class AllTeamPresenter extends Presenter {
    private static final String TAG = "AllTeamPresenter";
    private IAllTeamView allTeamView;
    private INBARespository nbaRespository;
    private GetCategoryQuerySubscriber subscriber;

    public AllTeamPresenter(Context context, IAllTeamView allTeamView) {
        super(context);
        this.allTeamView = allTeamView;
        this.nbaRespository = NBARespository.getInstance();
    }

    public void initData() {
        rxJavaExecuter.execute(nbaRespository.getCategoryQuery(),
                subscriber = new GetCategoryQuerySubscriber());
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    private class GetCategoryQuerySubscriber extends Subscriber<TeamInfoBean> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            Log.e(TAG, e.getMessage());
            if (subscriber != null) {
                subscriber.unsubscribe();
            }
            if (allTeamView != null) {
                allTeamView.showAllTeamListInfoError(ErrorExceptionUtil.getErrorMsg(e));
            }
        }

        @Override
        public void onNext(TeamInfoBean teamInfoBean) {
            Log.e(TAG, teamInfoBean.toString());
            if (allTeamView != null) {
                allTeamView.showAllTeamListInfoSuccess(teamInfoBean);
            }
            this.onCompleted();
        }
    }
}
