package com.example.cookbook.model.executor;

import android.support.annotation.NonNull;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by xuwei on 2017/7/28.
 */

public class JobExecutor implements ThreadExecutor {
    private static final int INITIAL_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 10;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
    private BlockingQueue<Runnable> workBlockQueue;
    private ThreadPoolExecutor threadPoolExecutor = null;
    private ThreadFactory threadFactory = null;

    private JobExecutor() {
        this.workBlockQueue = new LinkedBlockingQueue<>();
        this.threadFactory = new JobFactory();
        this.threadPoolExecutor = new ThreadPoolExecutor(INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, workBlockQueue, threadFactory);
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable to execute cannot be null");
        }
        this.threadPoolExecutor.execute(runnable);
    }

    public static JobExecutor getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        static JobExecutor INSTANCE = new JobExecutor();
    }

    private static class JobFactory implements ThreadFactory {
        private static final String THREAD_NAME = "android_";
        private static int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable, THREAD_NAME + counter);
            counter++;
            return thread;
        }
    }
}
