package com.example.guo.nbalover.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guo.nbalover.R;
import com.example.guo.nbalover.base.BAdapter;
import com.example.guo.nbalover.base.ViewHolder;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;

import java.util.List;

/**
 * Created by xuwei on 2017/7/27.
 */

public class TeamListAdapter extends BAdapter<TeamInfoBean.ResultBean.TeamBean> {
    public TeamListAdapter(Context context, List data) {
        super(context, data, R.layout.team_item);
    }
    @Override
    public void convert(ViewHolder holder, TeamInfoBean.ResultBean.TeamBean teamBean) {
        ((TextView) holder.getView(R.id.tv_fullName)).setText(teamBean.getFull_name());
        ((TextView) holder.getView(R.id.tv_location)).setText(teamBean.getCn_division());
        ((TextView) holder.getView(R.id.tv_teamInfo)).setText(teamBean.getIntro());
        ((ImageView) holder.getView(R.id.iv_logo)).setImageResource(android.R.drawable.ic_media_play);

    }
}
