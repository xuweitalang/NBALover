package com.example.guo.nbalover.biz;

import com.example.guo.nbalover.Presenter.ILoginListener;
import com.example.guo.nbalover.model.Entity.UserBean;

/**
 * Created by xuwei on 2017/7/8.
 */

public class LoginBiz {
    public void login(String userName, String password, ILoginListener listener) {
        if(userName.equals("xuwei") && password.equals("123")) {
            UserBean userBean = new UserBean();
            userBean.setUserName(userName);
            userBean.setPassword(password);
            listener.loginSuccess(userBean);
        }else {
            listener.loginFailure();
        }
    }
}
