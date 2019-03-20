package com.bwei.weidustore.fragment;

import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;

import com.bwei.weidustore.R;
import com.bwei.weidustore.adapter.ShopListAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.bean.AllBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.bwei.weidustore.custom.CustomSearchView;
import com.bwei.weidustore.model.HomeModel;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.utils.Contract;

import butterknife.BindView;

/**
 * @Auther: 李
 * @Date: 2019/2/14 12:16:19
 * @Description:
 */
public class HomeFragment extends BaseFragment<BasePresenter<HomeModel>> implements Contract.IHomeView {


    @BindView(R.id.custom_serch)
    CustomSearchView customSerch;
    @BindView(R.id.all_recyclerview)
    RecyclerView allRecyclerview;
    @BindView(R.id.scrollview)
    ScrollView scrollview;
    @BindView(R.id.search_rcview)
    RecyclerView searchRcview;

    @Override
    protected BasePresenter<HomeModel> getPresenter() {
        presenter = new HomePresenter(this);
        return presenter;
    }

    @Override
    protected int getlayoutResID() {
        return R.layout.home_layout;

    }

    @Override
    protected void initView() {

    }

    //初始化数据
    @Override
    protected void initData() {
        presenter.getShopList();
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
        AllBean build = builder.setMlss(result.getMlss())
                .setPzsh(result.getPzsh())
                .setRxxp(result.getRxxp())
                .build();
        allRecyclerview.setAdapter(new ShopListAdapter(build,getActivity()));
    }
}
