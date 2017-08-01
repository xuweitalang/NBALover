package com.example.guo.nbalover.Presenter;

import android.os.Handler;

import com.example.guo.nbalover.IView.ILoginView;
import com.example.guo.nbalover.base.BasePresenter;
import com.example.guo.nbalover.model.Entity.UserBean;

import com.example.guo.nbalover.biz.LoginBiz;

/**
 * Created by xuwei on 2017/7/8.
 */

public class LoginPresenter extends BasePresenter {
    private ILoginView loginView;
    private LoginBiz loginBiz;
    private Handler handler = new Handler();

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginBiz = new LoginBiz();
    }

    public void login(String userName, String password) {
        loginView.showLoading();
        loginBiz.login(userName, password, new ILoginListener() {
            @Override
            public void loginSuccess(UserBean userBean) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                        loginView.toMainActivity();
                    }
                });
            }

            @Override
            public void loginFailure() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        loginView.hideLoading();
                        loginView.showLoginFailView();
                    }
                });
            }
        });
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestory() {

    }
}
