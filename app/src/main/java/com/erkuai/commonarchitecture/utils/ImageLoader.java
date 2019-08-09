package com.erkuai.commonarchitecture.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.erkuai.commonarchitecture.base.BaseApplication;

/**
 * Created by Administrator on 2019/4/16.
 */

public class ImageLoader {

    public static void loadImage(String url, int holderResource, ImageView imageView) {
        Glide.with(BaseApplication.mContext).load(url)
                .apply(RequestOptions.placeholderOf(holderResource).dontAnimate())
                .into(imageView);
    }

    public static void loadImage(String url, ImageView imageView) {
        Glide.with(BaseApplication.mContext).load(url)
                .into(imageView);
    }

    public static void loadDrawableImage(int url, ImageView imageView) {
        Glide.with(BaseApplication.mContext).load(url)
                .into(imageView);
    }

    public static void loadCircleImage(String url, ImageView imageView) {
        Glide.with(BaseApplication.mContext).load(url)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);
    }

    public static void getUrlBitmap(String url, final RelativeLayout relativeLayout) {
        Glide.with(BaseApplication.mContext).asBitmap().load(url)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        relativeLayout.setBackground(new BitmapDrawable(resource));
                    }
                });
    }

}
