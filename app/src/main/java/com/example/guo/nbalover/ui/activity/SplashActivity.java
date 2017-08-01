package com.example.guo.nbalover.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.guo.nbalover.IView.ISplashView;
import com.example.guo.nbalover.R;

public class SplashActivity extends Activity implements ISplashView{
//    private SplashPresenter splashPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        splashPresenter = new SplashPresenter(this,this);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                    toLoginActivity();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        runnable.run();
    }

    @Override
    public void onSplashInitData() {
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
