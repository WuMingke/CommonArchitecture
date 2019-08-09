package com.erkuai.commonarchitecture.di.module;

import com.erkuai.commonarchitecture.base.BaseApplication;
import com.erkuai.commonarchitecture.di.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/8/9.
 */

@Module
public class AppModule {
    private BaseApplication baseApplication;

    public AppModule(BaseApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    BaseApplication getBaseApplication() {
        return baseApplication;
    }
}
