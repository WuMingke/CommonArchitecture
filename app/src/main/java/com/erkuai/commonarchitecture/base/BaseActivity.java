package com.erkuai.commonarchitecture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.erkuai.commonarchitecture.R;
import com.erkuai.commonarchitecture.di.component.ActivityComponent;
import com.erkuai.commonarchitecture.di.component.DaggerActivityComponent;
import com.erkuai.commonarchitecture.di.module.ActivityModule;
import com.erkuai.commonarchitecture.http.BasePresenter;
import com.erkuai.commonarchitecture.utils.Utils;

import org.simple.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/8/9.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {
    @Inject
    protected T mPresenter;


    //butterKnife
    private Unbinder mUnbinder;

    //这里可以做一些setContentView之前的操作
    protected void onCreateBefore() {
    }

    //初始化注入
    protected abstract void initInject(Bundle bundle);

    //布局
    protected abstract int getLayout();

    //初始化操作和加载数据
    protected abstract void initEventAndData();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        onCreateBefore();
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        LinearLayout linearLayout = ButterKnife.findById(this, R.id.base_view);
        linearLayout.addView(getLayoutInflater().inflate(getLayout(), null), ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mUnbinder = ButterKnife.bind(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        initInject(savedInstanceState);

        if (mPresenter != null) {
            mPresenter.attachView(this);
        }

        initEventAndData();

        EventBus.getDefault().register(this);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
        EventBus.getDefault().unregister(this);

/*        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .remove(fragment)
                        .commit();
            }
        }*/
        Log.i("activity_name", "Activity-onDestroy()----" + getClass().getSimpleName());
    }

    @Override
    public void showToast(String msg) {//制作一个弹窗
        Utils.showToast(this, msg);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(BaseApplication.getAppComponent())
                .activityModule(new ActivityModule(this)).build();
    }
}
