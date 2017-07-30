package com.example.cookbook.net;

import android.support.compat.BuildConfig;

import com.example.cookbook.AppConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xuwei on 2017/7/28.
 */

public class RetrofitService {
    private static final String TAG = "RetrofitService";
    private static RetrofitService instance;

    public static RetrofitService getInstance() {
        if (instance == null) {
            instance = new RetrofitService();
        }
        return instance;
    }

    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private RetrofitService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
            okHttpClient = new OkHttpClient();
        }
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public <T> T createApi(Class<T> clazz) {
        return retrofit.create(clazz);
    }

}
