package com.example.cookbook.ui.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.cookbook.R;
import com.example.cookbook.model.entity.AllCookBean;
import com.example.cookbook.ui.adapter.StepListAdapter;
import com.example.cookbook.utils.GlideUtil;

import butterknife.Bind;

public class CookDetailActivity extends BaseActivity {

    @Bind(R.id.iv_album)
    ImageView ivAlbum;
    @Bind(R.id.tv_tags)
    TextView tvTags;
    @Bind(R.id.tv_imtro)
    TextView tvImtro;
    @Bind(R.id.tv_ingredients)
    TextView tvIngredients;
    @Bind(R.id.tv_burden)
    TextView tvBurden;
    @Bind(R.id.tv_steps)
    TextView tvSteps;
    @Bind(R.id.lv_step)
    ListView lvStep;

    private AllCookBean.ResultBean.DataBean dataBean;
    private GlideUtil glideUtil;
    private StepListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_cook_detail;
    }

    @Override
    protected void initData() {
        dataBean = (AllCookBean.ResultBean.DataBean) getIntent().getSerializableExtra("cook_detail");
        glideUtil = new GlideUtil();
        if (dataBean != null) {
            tvTitle.setText(dataBean.getTitle());
            glideUtil.attach(ivAlbum).injectImage(dataBean.getAlbums().get(0));
            tvTags.setText("标签：" + dataBean.getTags());
            tvBurden.setText("佐料：" + dataBean.getBurden());
            tvImtro.setText("说明：" + dataBean.getImtro());
            tvIngredients.setText("主料：" + dataBean.getIngredients());
            adapter = new StepListAdapter(this, dataBean.getSteps());
            lvStep.setAdapter(adapter);
//            tvSteps.setText("步骤:" + dataBean.getSteps().toString());
        }
    }

    @Override
    protected void initView() {
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean openEventBus() {
        return false;
    }
}
