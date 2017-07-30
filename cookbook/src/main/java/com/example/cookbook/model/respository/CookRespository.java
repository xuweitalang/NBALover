package com.example.cookbook.model.respository;

import com.example.cookbook.AppConstant;
import com.example.cookbook.model.entity.AllCookBean;
import com.example.cookbook.net.ICookService;
import com.example.cookbook.net.RetrofitService;
import com.google.gson.Gson;

import rx.Observable;

/**
 * Created by xuwei on 2017/7/28.
 */

public class CookRespository implements ICookRespository {
    private Gson gson;

    private CookRespository() {
        gson = new Gson();
    }

    private static CookRespository instance;

    public static CookRespository getInstance() {
        if (instance == null) {
            instance = new CookRespository();
        }
        return instance;
    }

    @Override
    public Observable<AllCookBean> getCategoryQuery(String menu, String size) {
        ICookService cookService = RetrofitService.getInstance().createApi(ICookService.class);
        return cookService.getCategoryQuery(AppConstant.CookBook_Api_Key, menu, size);
    }
}
