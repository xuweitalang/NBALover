package com.example.guo.nbalover.Presenter;

import android.content.Context;

import com.example.guo.nbalover.model.executer.JobExecutor;
import com.example.guo.nbalover.model.executer.RxJavaExecuter;
import com.example.guo.nbalover.model.executer.UIThread;

/**
 * Created by guo on 2017/4/4.
 */

public abstract class Presenter implements IPresenter{
    protected Context context;
    protected RxJavaExecuter rxJavaExecuter;
    public Presenter(Context context) {
        this.context = context;
        this.rxJavaExecuter = new RxJavaExecuter(JobExecutor.instance(), UIThread.getInstance());
    }
}
