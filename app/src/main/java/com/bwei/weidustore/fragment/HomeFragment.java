package com.bwei.weidustore.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ScrollView;

import com.bwei.weidustore.R;
import com.bwei.weidustore.SearchActivity;
import com.bwei.weidustore.adapter.GoodsLvOneAdapter;
import com.bwei.weidustore.adapter.GoodsLvTwoAdapter;
import com.bwei.weidustore.adapter.ShopListAdapter;
import com.bwei.weidustore.base.BaseFragment;
import com.bwei.weidustore.bean.AllBean;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.GoodsLvOneBean;
import com.bwei.weidustore.bean.GoodsLvTwoBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.bwei.weidustore.custom.CustomSearchView;
import com.bwei.weidustore.presenter.HomePresenter;
import com.bwei.weidustore.utils.Contract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

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
    private RecyclerView lv_one_recyclerView;
    private RecyclerView lv_two_recyclerView;


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
        EventBus.getDefault().register(this);
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
        customSerch.setOnEditSearchClickListener(new CustomSearchView.setOnBtnSearchClickListener() {
            @Override
            public void setOnBtnSearchClick(String searchdata) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("searchdata",searchdata);
                startActivity(intent);
            }
        });
        customSerch.setSetOnImgSearchClickListener(new CustomSearchView.setOnImgSearchClickListener() {
            @Override
            public void setOnImgSearchClick() {
                //初始化PopupWindow
                initPopupWindow();
            }
        });
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

    //初始化PopupWindow
    public void initPopupWindow() {
        View popupWindow_layout = LayoutInflater.from(getActivity()).inflate(R.layout.popupwindowd_layout, null, false);
        //设置弹窗透明度
        popupWindow_layout.setAlpha(0.9f);
        PopupWindow popupWindow = new PopupWindow(popupWindow_layout, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        // 设置PopupWindow的弹出和消失效果
        //自定义文件设置动画
        popupWindow.setAnimationStyle(R.style.popupAnimation);
        //设置背景色
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        // 设置PopupWindow是否能响应外部点击事件
        popupWindow.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        popupWindow.setTouchable(true);
        //设置显示位置
        popupWindow.showAsDropDown(customSerch.findViewById(R.id.img_search));
        //popupWindow控件
        lv_one_recyclerView = popupWindow_layout.findViewById(R.id.lv_One_RecyclerView);
        lv_two_recyclerView = popupWindow_layout.findViewById(R.id.lv_Two_RecyclerView);
        //布局
        lv_one_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.HORIZONTAL,false));
        lv_two_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),OrientationHelper.HORIZONTAL,false));
        //默认加载数据
        presenter.getLvOneData();
        //默认加载二级类目
        presenter.getLvTwoData("1001002");
    }

    //获取一级类目ID
    @Subscribe(sticky = true)
    public void getMsy(String firstCategoryId) {
        if (!TextUtils.isEmpty(firstCategoryId)) {
            presenter.getLvTwoData(firstCategoryId);
        }
    }

    //一级类目
    @Override
    public void getLvOneData(Object o) {
        GoodsLvOneBean goodsLvOneBean = (GoodsLvOneBean) o;
        List<GoodsLvOneBean.ResultBean> result = goodsLvOneBean.getResult();
        GoodsLvOneAdapter goodsLvOneAdapter = new GoodsLvOneAdapter(result,getActivity());
        lv_one_recyclerView.setAdapter(goodsLvOneAdapter);

    }
    //二级类目
    @Override
    public void getLvTwoData(Object o) {
        GoodsLvTwoBean goodsLvTwoBean = (GoodsLvTwoBean) o;
        List<GoodsLvTwoBean.ResultBean> result = goodsLvTwoBean.getResult();
        if (result != null){
            GoodsLvTwoAdapter goodsLvTwoAdapter = new GoodsLvTwoAdapter(result,getActivity());
            lv_two_recyclerView.setAdapter(goodsLvTwoAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
