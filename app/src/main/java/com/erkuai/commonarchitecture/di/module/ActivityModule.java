package com.erkuai.commonarchitecture.di.module;

import android.app.Activity;

import com.erkuai.commonarchitecture.di.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/8/9.
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    Activity getActivity() {
        return activity;
    }
}
