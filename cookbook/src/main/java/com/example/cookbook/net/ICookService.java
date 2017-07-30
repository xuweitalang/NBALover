package com.example.cookbook.net;

import com.example.cookbook.AppConstant;
import com.example.cookbook.model.entity.AllCookBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by xuwei on 2017/7/28.
 */

public interface ICookService {
    @GET(AppConstant.Cook_Query)
    Observable<AllCookBean> getCategoryQuery(@Query(AppConstant.CookBook_Parameter_Key) String key,
                                             @Query(AppConstant.CookBook_Parameter_Menu) String menu,
                                             @Query(AppConstant.CookBook_Parameter_Size) String size);
}
