package com.erkuai.commonarchitecture.di.component;

import com.erkuai.commonarchitecture.base.BaseApplication;
import com.erkuai.commonarchitecture.di.ContextLife;
import com.erkuai.commonarchitecture.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2019/8/9.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    BaseApplication getApplication();
}
