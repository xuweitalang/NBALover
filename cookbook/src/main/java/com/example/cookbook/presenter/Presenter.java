package com.example.cookbook.presenter;

import android.content.Context;

import com.example.cookbook.model.executor.JobExecutor;
import com.example.cookbook.model.executor.RxJavaExecutor;
import com.example.cookbook.model.executor.UIThread;

/**
 * Created by xuwei on 2017/7/28.
 */

public abstract class Presenter {
    protected Context context;
    protected RxJavaExecutor executor;

    public Presenter(Context context) {
        this.context = context;
        executor = new RxJavaExecutor(JobExecutor.getInstance(), UIThread.getInstance());
    }
    protected abstract void destory();
}
