package com.example.mvpdemo.view.myView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.mvpdemo.R;

/**
 * Created by xuwei on 2017/4/23.
 * 自定义仿ISO的dialog
 */

public class MikyouCommonDialog extends Dialog implements View.OnClickListener {

    private Context context;
    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_negativeButton;
    private TextView tv_positiveButton;
    private FrameLayout flContent;
    private View mViewContent;


    private String mTitleText;
    private String mContentText;
    private boolean mShowCancel;
    private boolean mShowContent;
    private boolean mShowTitle;
    private String mCancelText;
    private String mConfirmText;
    private OnClickListener mCancelClickListener;
    private OnClickListener mConfirmClickListener;

    public interface OnClickListener {
        void onClick(MikyouCommonDialog mikyouCommonDialog);
    }

    //只有确定的dialog
    public MikyouCommonDialog(Context context, String mTitleText) {
        this(context, "", mTitleText, "确定", "");
        showCancelButton(false);
        showContentText(false);
    }


    //按钮为确定和取消的不包含View的dialog
    public MikyouCommonDialog(Context context, String mContentText, String mTitleText) {
        this(context, mContentText, mTitleText, "确定", "取消");
        showCancelButton(true);
    }

    //按钮为确定和取消包含View的dialog
    public MikyouCommonDialog(Context context, View mViewContent, String mTitleText) {
        this(context, mViewContent, mTitleText, "确定", "取消");
        showCancelButton(true);
    }

    //带有自定义view的构造器
    public MikyouCommonDialog(Context context, View mViewContent, String mTitleText, String mConfirmText, String mCancelText) {
        super(context, R.style.alert_dialog);
        this.context = context;
        this.mTitleText = mTitleText;
        this.mConfirmText = mConfirmText;
        this.mCancelText = mCancelText;
        this.mViewContent = mViewContent;
    }

    //不带自定义view的构造器
    public MikyouCommonDialog(Context context, String mContentText, String mTitleText, String mConfirmText, String mCancelText) {
        super(context, R.style.alert_dialog);
        this.context = context;
        this.mTitleText = mTitleText;
        this.mContentText = mContentText;
        this.mConfirmText = mConfirmText;
        this.mCancelText = mCancelText;
        showContentText(false);
    }

    //单个按钮且只包含自定义View的dialog
    public MikyouCommonDialog(Context context, View mViewContent, String mTitleText, String mConfirmText) {
        super(context, R.style.alert_dialog);
        this.context = context;
        this.mTitleText = mTitleText;
        this.mConfirmText = mConfirmText;
        this.mViewContent = mViewContent;
    }

    //单个按钮且不包含自定义View的dialog
    public MikyouCommonDialog(Context context, String mContentText, String mTitleText, String mConfirmText) {
        super(context, R.style.alert_dialog);
        this.context = context;
        this.mTitleText = mTitleText;
        this.mConfirmText = mConfirmText;
        this.mContentText = mContentText;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.dialog);
        setCanceledOnTouchOutside(false);
        tv_title = (TextView) findViewById(R.id.title);
        tv_message = (TextView) findViewById(R.id.message);
        tv_negativeButton = (TextView) findViewById(R.id.negativeButton);
        tv_positiveButton = (TextView) findViewById(R.id.positiveButton);
        flContent = (FrameLayout) findViewById(R.id.flContent);
        View line = findViewById(R.id.line);
        tv_negativeButton.setOnClickListener(this);
        tv_positiveButton.setOnClickListener(this);

        if (mViewContent != null) {
            setSweetContentView(mViewContent);
        }

        if (mShowCancel) {
            showCancelButton(true);
            line.setVisibility(View.VISIBLE);
        } else {
            line.setVisibility(View.GONE);
        }

        if (mShowContent) {
            showContentText(true);
        }


        setTitleText(mTitleText);
        setContentText(mContentText);
        setCancelText(mCancelText);
        setConfirmText(mConfirmText);
    }

    private MikyouCommonDialog setSweetContentView(View contentView) {
        this.mViewContent = contentView;
        if (flContent != null) {
            flContent.removeAllViews();
            flContent.setVisibility(View.VISIBLE);
            flContent.addView(mViewContent, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
        }
        return this;
    }

    public boolean isShowCancelButton() {
        return mShowCancel;
    }

    public MikyouCommonDialog showCancelButton(boolean isShow) {
        mShowCancel = isShow;
        if (tv_negativeButton != null) {
            tv_negativeButton.setVisibility(mShowCancel ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public boolean isShowContentText() {
        return mShowContent;
    }

    private MikyouCommonDialog showContentText(boolean isShow) {
        mShowContent = isShow;
        if (tv_message != null) {
            tv_message.setVisibility(mShowContent ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    private MikyouCommonDialog showTitleText(boolean isShow) {
        mShowTitle = isShow;
        if (tv_title != null) {
            tv_title.setVisibility(mShowTitle ? View.VISIBLE : View.GONE);
        }
        return this;
    }

    public String getCancelText() {
        return mCancelText;
    }

    private MikyouCommonDialog setCancelText(String text) {
        mCancelText = text;
        if (tv_negativeButton != null && mCancelText != null) {
            showCancelButton(true);
            tv_negativeButton.setText(mCancelText);
        }
        return this;
    }

    public String getTitleText() {
        return mTitleText;
    }

    private MikyouCommonDialog setTitleText(String text) {
        mTitleText = text;
        if (tv_title != null && mTitleText != null) {
            tv_title.setText(mTitleText);
        }
        showTitleText(text != null);
        return this;
    }

    public String getContentText() {
        return mContentText;
    }

    private MikyouCommonDialog setContentText(String text) {
        mContentText = text;
        if (tv_message != null && mContentText != null) {
            showContentText(true);
            tv_message.setText(mContentText);
        }
        return this;
    }

    public String getConfirmText() {
        return mConfirmText;
    }

    private MikyouCommonDialog setConfirmText(String text) {
        mConfirmText = text;
        if (tv_positiveButton != null && mConfirmText != null) {
            tv_positiveButton.setText(mConfirmText);
        }
        return this;
    }

    public MikyouCommonDialog setCancelClickListener(OnClickListener listener) {
        mCancelClickListener = listener;
        return this;
    }

    public MikyouCommonDialog setConfirmClickListener(OnClickListener listener) {
        mConfirmClickListener = listener;
        return this;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.negativeButton:
                if (mCancelClickListener != null) {
                    mCancelClickListener.onClick(MikyouCommonDialog.this);
                }
                break;
            case R.id.positiveButton:
                if (mConfirmClickListener != null) {
                    mConfirmClickListener.onClick(MikyouCommonDialog.this);
                }
                break;
        }
    }
}
