package com.erkuai.commonarchitecture.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.di.component.DaggerFragmentComponent;
import com.erkuai.commonarchitecture.di.component.FragmentComponent;
import com.erkuai.commonarchitecture.di.module.FragmentModule;
import com.erkuai.commonarchitecture.http.BasePresenter;
import com.erkuai.commonarchitecture.utils.Utils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/8/9.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    @Inject
    protected T mPresenter;

    protected Context mContext;

    private Unbinder mUnBinder;

    //初始化注入
    protected abstract void initInject(Bundle bundle);

    //布局
    protected abstract int getLayout();

    //这里可以做一些setContentView之前的操作
    protected abstract void onCreateBefore();

    //初始化操作和加载数据
    protected abstract void initEventAndData();

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View baseView = inflater.inflate(R.layout.fargment_base, null);
        LinearLayout base_view = ButterKnife.findById(baseView, R.id.base_view);

        View newView = inflater.inflate(getLayout(), null);
        base_view.addView(newView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        onCreateBefore();


        return baseView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initInject(savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        if (savedInstanceState == null) {
            if (!isHidden()) {
                initEventAndData();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
    }

    @Override
    public void showToast(String msg) {
        Utils.showToast(mContext, msg);
    }
}
