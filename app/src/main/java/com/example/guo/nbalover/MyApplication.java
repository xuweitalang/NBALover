package com.example.guo.nbalover;

import android.app.Application;
import android.content.Context;

/**
 * Created by xuwei on 2017/7/27.
 */

public class MyApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public Context getContext() {
        return mContext;
    }
}
