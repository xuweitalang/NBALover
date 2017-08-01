package com.example.guo.nbalover.model.Respository;

import com.example.guo.nbalover.constants.AppConstants;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;
import com.example.guo.nbalover.model.interfaces.INBARespository;
import com.example.guo.nbalover.model.interfaces.INBAService;
import com.example.guo.nbalover.net.RetrofitService;
import com.google.gson.Gson;

import rx.Observable;

/**
 * Created by guo on 2017/4/4.
 */

public class NBARespository implements INBARespository {
    private static NBARespository Instance = null;

    public static NBARespository getInstance() {
        if (Instance == null) {
            Instance = new NBARespository();
        }
        return Instance;
    }

    private Gson gson;

    private NBARespository() {
        gson = new Gson();
    }

    @Override
    public Observable<TeamInfoBean> getCategoryQuery() {
        INBAService service = RetrofitService.getInstance().createApi(INBAService.class);
        return service.getCategoryQuery(AppConstants.NBA_MobAPI_Key);
    }
}
