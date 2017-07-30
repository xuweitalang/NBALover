package com.example.cookbook.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookbook.R;
import com.example.cookbook.model.entity.AllCookBean;

import java.util.List;

/**
 * Created by xuwei on 2017/7/27.
 */

public class CookListAdapter extends BAdapter<AllCookBean.ResultBean.DataBean> {
    public CookListAdapter(Context context, List data) {
        super(context, data, R.layout.team_item);
    }
    @Override
    public void convert(ViewHolder holder, AllCookBean.ResultBean.DataBean dataBean) {
        ((TextView) holder.getView(R.id.tv_fullName)).setText(dataBean.getTitle());
        ((TextView) holder.getView(R.id.tv_location)).setText(dataBean.getTags());
//        ((TextView) holder.getView(R.id.tv_teamInfo)).setText(dataBean.getImtro());
        ((ImageView) holder.getView(R.id.iv_logo)).setImageResource(android.R.drawable.ic_media_play);

    }
}
