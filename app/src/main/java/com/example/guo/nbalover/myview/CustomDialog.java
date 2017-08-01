package com.example.guo.nbalover.myview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.guo.nbalover.R;


public class CustomDialog extends Dialog {
    
    public CustomDialog(Context context ,int layout, boolean isAnim) { 
    	super(context, R.style.MyDialogStyle);
    	setContentView(layout);
    }
    /**
     * 描述：默认提示文字为：loading
     * 创建： 吕
     * 日期： 2017/4/7
     */
    public CustomDialog(Context context) { 
    	super(context, R.style.MyDialogStyle);
    	setContentView(R.layout.dialog_loading);
    }

	/**
	 * 支持修改底部提示文字
	 * @param context
	 * @param toast
	 */
    public CustomDialog(Context context, String toast) { 
    	super(context, R.style.MyDialogStyle);
    	View view = View.inflate(context, R.layout.dialog_loading, null);
    	TextView tv_toast = (TextView) view.findViewById(R.id.tv_toast);
    	tv_toast.setVisibility(View.VISIBLE);
    	tv_toast.setText(toast);
    	setContentView(view);
    }

}
