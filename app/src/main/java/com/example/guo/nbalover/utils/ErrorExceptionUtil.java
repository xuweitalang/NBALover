package com.example.guo.nbalover.utils;

/**
 * Created by xuwei on 2017/7/27.
 */

public class ErrorExceptionUtil {
    public static String getErrorMsg(Throwable e) {
        String msg = null;
        String err = e.getMessage();
        if (e == null || err == null) {
            return "网络故障";
        }
        if (err.equals("No address associated with hostname")) {
            msg = "网络没有连接";
        } else {
            msg = "网络故障";
        }
        return msg;
    }
}
