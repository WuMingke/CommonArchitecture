package com.erkuai.commonarchitecture.di.component;

import android.app.Activity;

import com.erkuai.commonarchitecture.di.FragmentScope;
import com.erkuai.commonarchitecture.di.module.FragmentModule;
import com.erkuai.commonarchitecture.ui.AFragment;

import dagger.Component;

/**
 * Created by Administrator on 2019/8/9.
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(AFragment fragment);
}
