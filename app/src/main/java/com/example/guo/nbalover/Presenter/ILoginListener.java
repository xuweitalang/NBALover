package com.example.guo.nbalover.Presenter;

import com.example.guo.nbalover.model.Entity.UserBean;

/**
 * Created by xuwei on 2017/7/8.
 */

public interface ILoginListener {
    void loginSuccess(UserBean userBean);
    void loginFailure();
}
