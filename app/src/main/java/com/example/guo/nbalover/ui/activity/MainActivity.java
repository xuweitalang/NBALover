package com.example.guo.nbalover.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.guo.nbalover.IView.IAllTeamView;
import com.example.guo.nbalover.Presenter.AllTeamPresenter;
import com.example.guo.nbalover.R;
import com.example.guo.nbalover.base.BaseActivity;
import com.example.guo.nbalover.model.Entity.TeamInfoBean;
import com.example.guo.nbalover.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements IAllTeamView {
    @Bind(R.id.lv)
    ListView lv;

    private AllTeamPresenter allTeamPresenter;
//    private TeamListAdapter teamListAdapter;
    private List<TeamInfoBean.ResultBean.TeamBean> teamBeanList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        teamListAdapter = new TeamListAdapter(this, teamBeanList);
//        lv.setAdapter(teamListAdapter);
    }

    @Override
    protected boolean openEventBus() {
        return false;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        allTeamPresenter = new AllTeamPresenter(this, this);
        allTeamPresenter.initData();
    }

    @Override
    public void doClick() {

    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void showAllTeamListInfoSuccess(TeamInfoBean teamInfoBean) {
        Log.e("",teamInfoBean.toString());
//        teamListAdapter.setDataList(teamInfoBean.getResult().getTeamBeanList());
//        teamListAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllTeamListInfoError(String msg) {
        ToastUtil.showToast(this, msg);
    }
}
