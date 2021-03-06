package com.example.cookbook.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.cookbook.IView.ICookSearchView;
import com.example.cookbook.R;
import com.example.cookbook.model.entity.AllCookBean;
import com.example.cookbook.presenter.CookSearchPresenter;
import com.example.cookbook.ui.adapter.CookListAdapter;
import com.example.cookbook.ui.myview.CustomDialog;
import com.example.cookbook.utils.ToastUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

import butterknife.Bind;
import okhttp3.logging.HttpLoggingInterceptor;

public class MainActivity extends BaseActivity implements ICookSearchView, View.OnClickListener {
    @Bind(R.id.lv)
    ListView lv;
    @Bind(R.id.et_searchCook)
    EditText et_searchCook;
    @Bind(R.id.btn_search)
    Button btn_search;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private String searchContent = "";
    private String lastSearchContent = "";
    private Handler handler;
    private int size = 10;
    private CustomDialog customDialog;
    private CookSearchPresenter searchPresenter;
    private CookListAdapter adapter;
    private ArrayList<AllCookBean.ResultBean.DataBean> dataBeenList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        handler = new Handler();
        searchPresenter = new CookSearchPresenter(this, this);
        adapter = new CookListAdapter(this, dataBeenList);
        lv.setAdapter(adapter);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(final RefreshLayout refreshlayout) {
                ((View) refreshlayout).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        size += 10;
                        loadData(searchContent, size);
                        refreshlayout.finishLoadmore();
                    }
                }, 2000);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                refreshlayout.setEnableRefresh(false);
            }
        });
    }

    private void loadData(String menu, int page) {
        searchPresenter.searchCook(menu, page + "");
    }

    @Override
    protected void initView() {
        tvTitle.setText("主页");
        btn_search.setOnClickListener(this);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivityForSeria(CookDetailActivity.class,"cook_detail",adapter.getItem(position));
            }
        });
    }

    @Override
    protected boolean showToolbar() {
        return true;
    }

    @Override
    protected boolean openEventBus() {
        return false;
    }

    @Override
    public void cookSearchSuccess(ArrayList<AllCookBean.ResultBean.DataBean> list) {
        HttpLoggingInterceptor.Logger.DEFAULT.log(list.toString());
        adapter.setDataList(list);

    }

    @Override
    public void cookSearchError(String errMsg) {
        ToastUtil.showToast(this, errMsg);
    }

    @Override
    public void cookSearchEmpty() {

    }

    @Override
    public void onClick(View view) {
        searchContent = et_searchCook.getText().toString();
        switch (view.getId()) {
            case R.id.btn_search:
                if (searchContent.isEmpty()) {
                    ToastUtil.showToast(this, "请输入菜名");
                } else {
                    if (!searchContent.equals(lastSearchContent)) {
                        size = 10;
                    }
                    adapter.clearList();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            loadData(searchContent, size);
                        }
                    });
                    lastSearchContent = searchContent;
                }
                break;
        }
    }

    @Override
    public void showLoading() {
        if (customDialog == null) {
            customDialog = new CustomDialog(this, true);
        }
        customDialog.show();
    }

    @Override
    public void hideLoading() {
        if (customDialog != null && customDialog.isShowing()) {
            customDialog.dismiss();
        }
    }
}
