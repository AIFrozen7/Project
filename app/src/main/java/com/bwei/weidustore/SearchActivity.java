package com.bwei.weidustore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.bwei.weidustore.adapter.SearchAdapter;
import com.bwei.weidustore.base.BaseActivity;
import com.bwei.weidustore.bean.SearchBean;
import com.bwei.weidustore.custom.CustomSearchView;
import com.bwei.weidustore.presenter.SearchPresenter;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.onLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchPresenter> implements Contract.ISearchView {

    @BindView(R.id.custom_serch)
    CustomSearchView customSerch;
    @BindView(R.id.search_rcview)
    RecyclerView searchRcview;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    private int page = 1;
    private Handler handler = new Handler();
    private SearchAdapter searchAdapter;
    private List<SearchBean.ResultBean> list = new ArrayList<>();

    @Override
    protected SearchPresenter getPresenter() {
        presenter = new SearchPresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        //设置搜索recyclerView布局
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        searchRcview.setLayoutManager(gridLayoutManager);

    }

    @Override
    protected void initData() {
        //默认加载page=1
        Intent intent = getIntent();
        String keyword = intent.getStringExtra("searchdata");
        presenter.getSearchData(page,keyword);
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
                        if (searchAdapter!=null){
                            list.clear();
                            if (presenter!=null){
                                Intent intent = getIntent();
                                String keyword = intent.getStringExtra("searchdata");
                                presenter.getSearchData(1,keyword);
                                swiperefreshlayout.setRefreshing(false);
                                Toast.makeText(SearchActivity.this, "亲,这边已经刷新好了呢", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                },2000);
            }
        });
        //加载
        //加载
        searchRcview.addOnScrollListener(new onLoadMoreListener() {
            @Override
            protected void onLoading(int countItem, int lastItem) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (presenter!=null){
                            Intent intent = getIntent();
                            String keyword = intent.getStringExtra("searchdata");
                            page++;
                            presenter.getSearchData(page,keyword);
                            Toast.makeText(SearchActivity.this, "亲,这边已经加载好了呢", Toast.LENGTH_SHORT).show();
                        }
                    }
                },2000);
            }
        });
        customSerch.setOnEditSearchClickListener(new CustomSearchView.setOnBtnSearchClickListener() {
            @Override
            public void setOnBtnSearchClick(String searchdata) {
                list.clear();
                presenter.getSearchData(page,searchdata);
            }
        });
    }

    //搜索
    @Override
    public void getSearchData(Object o) {
        SearchBean searchBean = (SearchBean) o;
        List<SearchBean.ResultBean> result = searchBean.getResult();
        list.addAll(result);
        searchAdapter = new SearchAdapter(list,this);
        searchRcview.setAdapter(searchAdapter);
    }
}
