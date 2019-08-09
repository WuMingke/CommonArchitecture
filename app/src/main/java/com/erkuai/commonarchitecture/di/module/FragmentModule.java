package com.erkuai.commonarchitecture.di.module;


import android.app.Activity;
import android.support.v4.app.Fragment;

import com.erkuai.commonarchitecture.di.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2019/8/9.
 */

@Module
public class FragmentModule {

    private Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @FragmentScope
    Activity getActivity() {
        return fragment.getActivity();
    }
}
