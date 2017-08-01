package com.example.guo.nbalover.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by xuwei on 2017/7/27.
 */

public abstract class BAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater inflater;
    private int layouyId;

    public BAdapter(Context context, List<T> data, int layouyId) {
        this.mContext = context;
        this.mData = data;
        this.layouyId = layouyId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //初始化ViewHolder,使用通用的ViewHolder，一行代码就搞定ViewHolder的初始化
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layouyId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    //将convert方法公布出去
    public abstract void convert(ViewHolder holder, T t);

    public void setDataList(List<T> data) {
        mData.clear();
        if (data != null) {
            mData.addAll(data);
        }
        notifyDataSetChanged();
    }

    public void clearList() {
        mData.clear();
        notifyDataSetChanged();
    }
}
