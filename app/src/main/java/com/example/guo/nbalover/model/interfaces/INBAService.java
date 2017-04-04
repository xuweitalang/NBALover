package com.example.guo.nbalover.model.interfaces;

import com.example.guo.nbalover.constants.AppConstants;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by guo on 2017/4/4.
 */

public interface INBAService {
    @GET(AppConstants.allTeamInfo)
    Observable<TeamInfoBean> getCategoryQuery(@Query(AppConstants.NBA_Parameter_Key) String key);
}
