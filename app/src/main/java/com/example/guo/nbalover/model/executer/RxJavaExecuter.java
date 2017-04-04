package com.example.guo.nbalover.model.executer;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by guo on 2017/4/4.
 */

public class RxJavaExecuter {
    private ThreadExecutor threadExecutor;
    private PostExecutionThread postExecutionThread;

    private Subscription subscription = Subscriptions.empty();

    public RxJavaExecuter(ThreadExecutor threadExecutor,PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public void execute(Observable observable, Subscriber subscriber) {
        /*
         * subscribeOn(): 指定 subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。
         * observeOn(): 指定 Subscriber 所运行在的线程。或者叫做事件消费的线程。
         *
         * 这种在 subscribe() 之前写上两句 subscribeOn(Scheduler.io()) 和 observeOn(AndroidSchedulers.mainThread())
         * 的使用方式非常常见，它适用于多数的 『后台线程取数据，主线程显示』的程序策略。
         */
        this.subscription = observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(subscriber);
    }
}
