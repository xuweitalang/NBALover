package com.example.cookbook.ui.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by xuwei on 2017/7/31.
 * 在ScrollView中嵌套ListView，由于ListView的父容器测量模式为UNSPECIFIED的时候，
 * ListView的高度默认为一个item的高度，所以这里需要重写ListView的onMeasure()方法。
 * 设置测量方式为AT_MOST。
 */

public class MyListView extends ListView {
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //把整数类型的最大值右移了2位，作为size传入，另外把一个AT_MOST的常量作为mode传入
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
