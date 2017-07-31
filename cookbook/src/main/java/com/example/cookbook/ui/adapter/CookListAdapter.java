package com.example.cookbook.ui.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cookbook.R;
import com.example.cookbook.model.entity.AllCookBean;
import com.example.cookbook.utils.GlideUtil;

import java.util.List;

/**
 * Created by xuwei on 2017/7/27.
 */

public class CookListAdapter extends BAdapter<AllCookBean.ResultBean.DataBean> {
    private GlideUtil glideUtil;
    public CookListAdapter(Context context, List data) {
        super(context, data, R.layout.cook_item);
        glideUtil = new GlideUtil();

    }
    @Override
    public void convert(ViewHolder holder, AllCookBean.ResultBean.DataBean dataBean) {
        ((TextView) holder.getView(R.id.tv_fullName)).setText(dataBean.getTitle());
        ((TextView) holder.getView(R.id.tv_location)).setText(dataBean.getTags());
//        ((TextView) holder.getView(R.id.tv_teamInfo)).setText(dataBean.getImtro());
        glideUtil.attach(((ImageView) holder.getView(R.id.iv_logo))).injectImage(dataBean.getAlbums().get(0));
//        ((ImageView) holder.getView(R.id.iv_logo)).setImageResource(android.R.drawable.ic_media_play);

    }
}
