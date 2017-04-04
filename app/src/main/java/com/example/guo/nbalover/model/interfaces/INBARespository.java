package com.example.guo.nbalover.model.interfaces;

import com.example.guo.nbalover.model.Entity.TeamInfoBean;

import rx.Observable;


/**
 * Created by guo on 2017/4/4.
 */

public interface INBARespository {
    public Observable<TeamInfoBean> getCategoryQuery();
}
