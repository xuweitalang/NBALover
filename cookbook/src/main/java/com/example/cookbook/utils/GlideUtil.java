package com.example.cookbook.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cookbook.R;

/**
 * Created by xuwei on 2017/7/30.
 */

public class GlideUtil {
    ImageView imageView;
    private DiskCacheStrategy diskCache = DiskCacheStrategy.ALL; //磁盘缓存
    private boolean isSkipMemoryCache = false; //禁止内存缓存

    public GlideUtil attach(ImageView imageView) {
        this.imageView = imageView;
        return this;
    }

    public GlideUtil injectImage(String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .diskCacheStrategy(diskCache)
                .skipMemoryCache(isSkipMemoryCache)
                .placeholder(R.mipmap.ic_icon_loading)
                .crossFade()
                .into(imageView);
        return this;
    }

    public GlideUtil injectImageWithoutCache(String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(isSkipMemoryCache)
                .placeholder(R.mipmap.ic_icon_loading)
                .crossFade()
                .into(imageView);
        return this;
    }

    public GlideUtil clearImage() {
        Glide.clear(imageView);
        imageView.setImageResource(R.mipmap.ic_icon_loading);
        return this;
    }
}
