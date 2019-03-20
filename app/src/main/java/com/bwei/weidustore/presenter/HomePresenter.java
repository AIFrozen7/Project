package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.DetailsModel;
import com.bwei.weidustore.model.HomeModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:05:16
 * @Description:
 */
public class HomePresenter extends BasePresenter<HomeModel> {
    Contract.IHomeView iHomeView;
    private  HomeModel homeModel;
    Contract.IDetailsView iDetailsView;
    private  DetailsModel detailsModel;

    public HomePresenter(Contract.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        homeModel = new HomeModel();
    }

    public HomePresenter(Contract.IDetailsView iDetailsView) {
        this.iDetailsView = iDetailsView;
        detailsModel = new DetailsModel();
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

    //商品详情信息
    @Override
    public void getData(int commodityId) {
        detailsModel.getData(commodityId, new Contract.IDetailsModel.IDetailsCallBack() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.getData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //详情(评论)
    @Override
    public void getCommentData(int commodityId) {
        detailsModel.getCommentData(commodityId, new Contract.IDetailsModel.IDetailsCallBack2() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.getCommentData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void addShopCarData(Map<String, String> map, RequestBody body) {

    }

    //登录
    @Override
    public void getLogin(String phone, String pwd) {

    }
}
