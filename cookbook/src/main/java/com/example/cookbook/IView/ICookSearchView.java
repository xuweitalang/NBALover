package com.example.cookbook.IView;

import com.example.cookbook.model.entity.AllCookBean;

import java.util.ArrayList;

/**
 * Created by xuwei on 2017/7/28.
 */

public interface ICookSearchView extends IView{
    void cookSearchSuccess(ArrayList<AllCookBean.ResultBean.DataBean> list);
    void cookSearchError(String errMsg);
    void cookSearchEmpty();
}
