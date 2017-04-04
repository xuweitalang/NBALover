package com.example.guo.nbalover.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guo.nbalover.IView.ISplashView;
import com.example.guo.nbalover.R;

public class SplashActivity extends AppCompatActivity implements ISplashView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onSplashInitData() {

    }
}
