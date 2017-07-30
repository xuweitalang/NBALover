package com.example.cookbook.model.executor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by xuwei on 2017/7/28.
 */

public class RxJavaExecutor {
    private ThreadExecutor mThreadExecutor;
    private PostExecutionThread mPostExecutionThread;

    public RxJavaExecutor(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.mPostExecutionThread = postExecutionThread;
        this.mThreadExecutor = threadExecutor;
    }

    private Subscription subscription = Subscriptions.empty();

    public void execute(Observable observable, Subscriber subscriber) {
        this.subscription = observable.subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(subscriber);
    }
}
