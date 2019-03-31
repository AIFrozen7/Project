package com.bwei.weidustore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.bwei.weidustore.adapter.GoodsLvThreeAdapter;
import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.GoodsLvThreeBean;
import com.bwei.weidustore.custom.CustomSearchView;
import com.bwei.weidustore.presenter.GoodsLvThreePresenter;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.onLoadMoreListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsLvThreeActivity extends BaseActivity<GoodsLvThreePresenter> implements Contract.IGoodsLvThreeView {

    @BindView(R.id.custom_serch)
    CustomSearchView customSerch;
    @BindView(R.id.lv_three_recyclerview)
    RecyclerView lvThreeRecyclerview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int page = 1;
    private Handler handler = new Handler();
    private List<GoodsLvThreeBean.ResultBean> list = new ArrayList<>();
    private GoodsLvThreeAdapter goodsLvThreeAdapter;
    private String categoryId1;

    @Override
    protected GoodsLvThreePresenter getPresenter() {
        presenter = new GoodsLvThreePresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_goods_lv_three;
    }

    @Override
    protected void initView() {
        //布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        lvThreeRecyclerview.setLayoutManager(gridLayoutManager);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {

    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initListener() {
        //圆圈颜色
        swiperefreshlayout.setColorSchemeColors(R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark);
        //圆圈背景
        swiperefreshlayout.setProgressBackgroundColorSchemeColor(R.color.colorwhite);
        //设置刷新时的操作
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (goodsLvThreeAdapter!=null){
                            list.clear();
                            if (presenter!=null){
                                presenter.getGoodsThreeData(categoryId1,1);
                                swiperefreshlayout.setRefreshing(false);
                                Toast.makeText(GoodsLvThreeActivity.this, "亲,这边已经刷新好了呢", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },2000);
            }
        });
        //加载
        //加载
        lvThreeRecyclerview.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (presenter!=null){
                            page++;
                            presenter.getGoodsThreeData(categoryId1,page);
                            Toast.makeText(GoodsLvThreeActivity.this, "亲,这边已经加载好了呢", Toast.LENGTH_SHORT).show();
                        }
                    }
                },2000);
            }
        });

        customSerch.setOnEditSearchClickListener(new CustomSearchView.setOnBtnSearchClickListener() {
            @Override
            public void setOnBtnSearchClick(String searchdata) {
                list.clear();
            }
        });
    }

    //获取二级类目ID
    @Subscribe(sticky = true)
    public void getMsy(String categoryId) {
        if (!TextUtils.isEmpty(categoryId)) {
            categoryId1 = categoryId;
            presenter.getGoodsThreeData(categoryId, page);
        }
    }


    //三级类目数据信息
    @Override
    public void getGoodsThreeData(Object o) {
        GoodsLvThreeBean goodsLvThreeBean = (GoodsLvThreeBean) o;
        List<GoodsLvThreeBean.ResultBean> result = goodsLvThreeBean.getResult();
        list.addAll(result);
        goodsLvThreeAdapter = new GoodsLvThreeAdapter(list,this);
        lvThreeRecyclerview.setAdapter(goodsLvThreeAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
