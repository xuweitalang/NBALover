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

public class StepListAdapter extends BAdapter<AllCookBean.ResultBean.DataBean.StepsBean> {
    private GlideUtil glideUtil;

    public StepListAdapter(Context context, List data) {
        super(context, data, R.layout.step_item);
        glideUtil = new GlideUtil();
    }

    @Override
    public void convert(ViewHolder holder, AllCookBean.ResultBean.DataBean.StepsBean dataBean) {
        ((TextView) holder.getView(R.id.tv_stepName)).setText(dataBean.getStep());
        glideUtil.attach(((ImageView) holder.getView(R.id.iv_step))).injectImage(dataBean.getImg());

    }
}
