package com.example.cookbook.model.executor;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by xuwei on 2017/7/28.
 */

public class UIThread implements PostExecutionThread {
    private UIThread(){}

    public static UIThread getInstance() {
        return Holder.instance;
    }
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
    private static final class Holder{
        private static UIThread instance = new UIThread();
    }
}
