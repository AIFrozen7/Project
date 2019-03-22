package com.bwei.weidustore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.bwei.weidustore.presenter.HomePresenter;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 李
 * @Date: 2019/3/14 19:02:48
 * @Description:
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    //在父类声明
    public T presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutResID());
        unbinder = ButterKnife.bind(this);
        presenter = getPresenter();
        presenter.attch(this);
        initView();
        initData();
        initListener();
    }

    //实例化P
    protected abstract T getPresenter();

    //获取布局ID
    protected abstract int getlayoutResID();
    //初始化控件
    protected abstract void initView();
    //初始化数据
    protected abstract void initData();
    //初始化监听
    protected abstract void initListener();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        presenter.detach();
    }
}
