package com.example.guo.nbalover.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.guo.nbalover.IView.ILoginView;
import com.example.guo.nbalover.Presenter.LoginPresenter;
import com.example.guo.nbalover.R;
import com.example.guo.nbalover.base.BaseActivity;
import com.example.guo.nbalover.myview.CustomDialog;

import butterknife.Bind;

public class LoginActivity extends BaseActivity implements ILoginView, View.OnClickListener {
    @Bind(R.id.et_pwd)
    EditText et_pwd;
    @Bind(R.id.et_user)
    EditText et_user;
    @Bind(R.id.btn_login)
    Button btn_login;
    @Bind(R.id.iv_saveUsername)
    ImageView iv_saveUsername;

    LoginPresenter presenter;
    private CustomDialog customDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPresenter(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        btn_login.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void doClick() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    protected boolean openEventBus() {
        return false;
    }

    @Override
    public void showLoading() {
        if(customDialog == null) {
            customDialog = new CustomDialog(this);
        }
        customDialog.show();
    }

    @Override
    public void hideLoading() {
        if (customDialog != null) {
            customDialog.dismiss();
        }
    }

    @Override
    public void showLoginFailView() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toMainActivity() {
        startActivity(new Intent(this,MainActivity.class));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                presenter.login(et_user.getText().toString(),et_pwd.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        customDialog = null;
    }
}
