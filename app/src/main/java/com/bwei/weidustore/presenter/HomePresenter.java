package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.DetailsModel;
import com.bwei.weidustore.model.HomeModel;
import com.bwei.weidustore.model.LoginModel;
import com.bwei.weidustore.model.MineModel;
import com.bwei.weidustore.model.RegistModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:05:16
 * @Description:
 */
public class HomePresenter extends BasePresenter<HomeModel> implements Contract.IHomePresenter {
    Contract.IHomeView iHomeView;
    private  HomeModel homeModel;


    public HomePresenter(Contract.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        homeModel = new HomeModel();
    }



    //商品信息
    @Override
    public void getShopList() {
        homeModel.getHomeData(new Contract.IHomeModel.IHomeCallBack() {
            @Override
            public void onSuccess(Object o) {
                iHomeView.getData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //轮播
    @Override
    public void getBannerData() {
        homeModel.getBannerData(new Contract.IHomeModel.IHomeCallBack() {
            @Override
            public void onSuccess(Object o) {
                iHomeView.getBannerData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
