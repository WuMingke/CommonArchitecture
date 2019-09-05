package com.erkuai.commonarchitecture.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.erkuai.commonarchitecture.BuildConfig;
import com.erkuai.commonarchitecture.di.component.AppComponent;
import com.erkuai.commonarchitecture.di.component.DaggerAppComponent;
import com.erkuai.commonarchitecture.di.module.AppModule;
import com.erkuai.commonarchitecture.ui.activities.MainActivity;
import com.erkuai.commonarchitecture.utils.ActivityManager;
import com.erkuai.commonarchitecture.widgets.UncaughtExceptionHandlerImpl;

/**
 * Created by Administrator on 2019/8/8.
 */

public class BaseApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                if (activity instanceof BaseActivity){
                    ActivityManager.addActivity(activity);
                }
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                ActivityManager.finishActivity(activity);
            }
        });

        // 设置崩溃后自动重启 APP
        // 参数依次为 上下文（建议是Application），是否是debug模式，是否崩溃后重启，重启延迟时间，重启的Activity
       // UncaughtExceptionHandlerImpl.getInstance().init(this, BuildConfig.DEBUG, false, 0, MainActivity.class);

    }

    public static AppComponent getAppComponent() {
        return DaggerAppComponent.builder().appModule(new AppModule((BaseApplication) mContext)).build();
    }
}
