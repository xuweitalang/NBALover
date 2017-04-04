package com.example.guo.nbalover.model.executer;

import rx.Scheduler;

/**
 * Created by PeOS on 2016/9/6 0006.
 */
public interface PostExecutionThread {
    /**
     * Scheduler:线程调度
     *
     * 在不指定线程的情况下， RxJava 遵循的是线程不变的原则，即：在哪个线程调用 subscribe()，
     * 就在哪个线程生产事件；在哪个线程生产事件，就在哪个线程消费事件。
     * 如果需要切换线程，就需要用到 Scheduler （调度器）。
     *
     * RxJava内置的几个Scheduler:
     * 1.Schedulers.immediate(): 直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
     *
     * 2.Schedulers.newThread(): 总是启用新线程，并在新线程执行操作。
     *
     * 3.Schedulers.io(): I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
     * 行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
     * 可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
     *
     * 4.Schedulers.computation(): 计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，
     * 即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。
     * 不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
     *
     * 5.Android 还有一个专用的 AndroidSchedulers.mainThread()，它指定的操作将在 Android 主线程运行。
     *
     */
    public Scheduler getScheduler();
}
