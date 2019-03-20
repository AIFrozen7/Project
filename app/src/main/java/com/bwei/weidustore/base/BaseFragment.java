package com.bwei.weidustore.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther: 李
 * @Date: 2019/3/19 13:59:14
 * @Description:
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public T presenter;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getlayoutResID(), container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = getPresenter();
        presenter.attch(this);
        initView();
        initData();
        initListener();
        return view;
    }

    //实例P层
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
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detach();
        unbinder.unbind();
    }
}
