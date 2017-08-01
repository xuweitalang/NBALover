package com.example.guo.nbalover.IView;

import com.example.guo.nbalover.model.Entity.TeamInfoBean;

/**
 * Created by xuwei on 2017/7/27.
 */

public interface IAllTeamView {
    void showAllTeamListInfoSuccess(TeamInfoBean teamInfoBean);
    void showAllTeamListInfoError(String msg);
}
