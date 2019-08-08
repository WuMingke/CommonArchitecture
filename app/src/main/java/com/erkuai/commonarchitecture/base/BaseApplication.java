package com.erkuai.commonarchitecture.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2019/8/8.
 */

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;



    }
}
