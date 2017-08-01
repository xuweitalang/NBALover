package com.example.guo.nbalover.manager;

import com.example.guo.nbalover.model.Entity.TeamInfoBean;

import java.util.ArrayList;

/**
 * Created by xuwei on 2017/7/27.
 */

public class AllTeamCategoryManager {
    private static AllTeamCategoryManager instance = null;
    private AllTeamCategoryManager(){}
    public static AllTeamCategoryManager getInstance() {
        if (instance == null) {
            instance = new AllTeamCategoryManager();
        }
        return instance;
    }

    public void initData(ArrayList<TeamInfoBean> teamInfoBeenList) {

    }
}
