package com.example.guo.nbalover.model.executer;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by guo on 2017/4/4.
 */

public class UIThread implements PostExecutionThread {

    private UIThread() {}
    public static UIThread getInstance() {
        return Holder.INSTANCE;
    }
    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

    private static class Holder {
        private static final UIThread INSTANCE = new UIThread();
    }
}
