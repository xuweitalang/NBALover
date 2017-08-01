package com.example.moreprocess;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by xuwei on 2017/7/26.
 */

public class WebappInterface {
    Context mContext;

    WebappInterface(Context context) {
        this.mContext = context;
    }

    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
