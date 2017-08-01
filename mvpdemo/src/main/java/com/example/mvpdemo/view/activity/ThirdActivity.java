package com.example.mvpdemo.view.activity;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.mvpdemo.R;
import com.lvfq.pickerview.adapter.ArrayWheelAdapter;
import com.lvfq.pickerview.lib.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwei on 2017/4/23.
 *
 * @viersion
 */

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn2third;
    Button btn;
    WheelView wv_option;
    WheelView wv_option1;
    List<String> list = new ArrayList<>();
    List<String> list1 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn2third = (Button) findViewById(R.id.btn2third);
        btn = (Button) findViewById(R.id.btn);
        btn2third.setOnClickListener(this);
        btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                showPopupWindow();
                break;
            case R.id.btn2third:
                break;
        }
    }

    private void showPopupWindow() {
        final PopupWindow popupWindow = new PopupWindow();

        View view = LayoutInflater.from(this).inflate(R.layout.layout_bottom_wheel_option, null);
        wv_option = (WheelView) view.findViewById(R.id.wv_option);
        wv_option1 = (WheelView) view.findViewById(R.id.wv_option1);
        TextView tv_confirm = (TextView) view.findViewById(R.id.btnSubmit);
//        final WheelView wv_option = (WheelView) view.findViewById(R.id.wv_option);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");

        list1.add("a");
        list1.add("b");
        list1.add("c");
        list1.add("d");
        list1.add("e");
        list1.add("f");
        wv_option.setAdapter(new ArrayWheelAdapter((ArrayList) list));
        wv_option.setCyclic(false);
        wv_option.setTextSize(16);
        wv_option1.setAdapter(new ArrayWheelAdapter((ArrayList) list));
        wv_option1.setCyclic(false);
        wv_option1.setTextSize(16);
        tv_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
//                WheelViewUtil.OnWheelViewClick.onClick(view, wv_option.getCurrentItem());
            }
        });

        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: 2016/8/11 0011 取消
                popupWindow.dismiss();
            }
        });
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int top = view.findViewById(R.id.ll_container).getTop();
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    int y = (int) motionEvent.getY();
                    if (y < top) {
                        popupWindow.dismiss();
                    }
                }
                return true;
            }
        });
        popupWindow.setContentView(view);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.showAtLocation(((ViewGroup) ((Activity) this).findViewById(android.R.id.content)).getChildAt(0), Gravity.CENTER, 0, 0);
    }
}
