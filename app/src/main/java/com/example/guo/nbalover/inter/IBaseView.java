package com.example.guo.nbalover.inter;

/**
 * Created by xuwei on 2017/7/8.
 */

public interface IBaseView {

    int getLayoutId();  //绑定布局
    void initView();  // 初始化View
    void initData(); //初始化数据
    void doClick(); // 点击事件
    void doBusiness(); //业务操作
}
