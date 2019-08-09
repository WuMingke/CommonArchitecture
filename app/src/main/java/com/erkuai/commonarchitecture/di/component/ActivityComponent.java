package com.erkuai.commonarchitecture.di.component;

import android.app.Activity;

import com.erkuai.commonarchitecture.di.ActivityScope;
import com.erkuai.commonarchitecture.di.module.ActivityModule;
import com.erkuai.commonarchitecture.ui.activities.MainActivity;
import com.erkuai.commonarchitecture.ui.activities.TextActivity;

import dagger.Component;

/**
 * Created by Administrator on 2019/8/9.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(MainActivity mainActivity);

    void inject(TextActivity textActivity);
}
