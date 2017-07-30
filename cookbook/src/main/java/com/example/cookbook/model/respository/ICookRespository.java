package com.example.cookbook.model.respository;

import com.example.cookbook.model.entity.AllCookBean;

import rx.Observable;

/**
 * Created by xuwei on 2017/7/28.
 */

public interface ICookRespository {
    Observable<AllCookBean> getCategoryQuery(String menu,String size);
}
