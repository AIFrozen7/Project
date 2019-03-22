package com.bwei.weidustore.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.ShopListAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.bean.AllBean;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.bwei.weidustore.custom.CustomSearchView;
import com.bwei.weidustore.model.HomeModel;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.utils.Contract;

import java.util.List;

import butterknife.BindView;

/**
 * @Auther: 李
 * @Date: 2019/2/14 12:16:19
 * @Description:
 */
public class HomeFragment extends BaseFragment<HomePresenter> implements Contract.IHomeView {


    @BindView(R.id.custom_serch)
    CustomSearchView customSerch;
    @BindView(R.id.all_recyclerview)
    RecyclerView allRecyclerview;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    private List<BannerBean.ResultBean> banner;


    @Override
    protected HomePresenter getPresenter() {
        presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.home_layout;

    }

    @Override
    protected void initView() {
        //设置recyclerView布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        allRecyclerview.setLayoutManager(linearLayoutManager);
    }

    //初始化数据
    @Override
    protected void initData() {
        presenter.getShopList();
        presenter.getBannerData();
    }

    //初始化监听
    @Override
    protected void initListener() {

    }

    //请商品求数据
    @Override
    public void getData(Object o) {
        ShopListBean shopListBean = (ShopListBean) o;
        ShopListBean.ResultBean result = shopListBean.getResult();

        AllBean.Builder builder = new AllBean.Builder();
        AllBean build = builder
                .setMlss(result.getMlss())
                .setPzsh(result.getPzsh())
                .setRxxp(result.getRxxp())
                .setResult(banner)
                .build();
        allRecyclerview.setAdapter(new ShopListAdapter(build, getActivity()));
    }

    //轮播图
    @Override
    public void getBannerData(Object o) {
        BannerBean bannerBean = (BannerBean) o;
        banner = bannerBean.getResult();
    }

}
