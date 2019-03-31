package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.GoodsLvOneBean;
import com.bwei.weidustore.bean.GoodsLvTwoBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:05:08
 * @Description:
 */
public class HomeModel implements Contract.IHomeModel {

    /**
     * 商品数据
     * @param iHomeCallBack
     */
    @Override
    public void getHomeData(final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<ShopListBean> shopList = iApi.getShopList();
        shopList.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ShopListBean>() {
                    @Override
                    public void onNext(ShopListBean shopListBean) {
                        iHomeCallBack.onSuccess(shopListBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 轮播图数据
     * @param iHomeCallBack
     */
    @Override
    public void getBannerData(final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<BannerBean> bannerData = iApi.getBannerData();
        bannerData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<BannerBean>() {
                    @Override
                    public void onNext(BannerBean bannerBean) {
                        iHomeCallBack.onSuccess(bannerBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 一级类目
     * @param iHomeCallBack
     */
    @Override
    public void getLvOneData(final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<GoodsLvOneBean> lvOneData = iApi.getLvOneData();
        lvOneData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<GoodsLvOneBean>() {
                    @Override
                    public void onNext(GoodsLvOneBean goodsLvOneBean) {
                        iHomeCallBack.onSuccess(goodsLvOneBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 二级类目
     * @param firstCategoryId
     * @param iHomeCallBack
     */
    @Override
    public void getLvTwoData(String firstCategoryId, final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<GoodsLvTwoBean> lvTwoData = iApi.getLvTwoData(firstCategoryId);
        lvTwoData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<GoodsLvTwoBean>() {
                    @Override
                    public void onNext(GoodsLvTwoBean goodsLvTwoBean) {
                        iHomeCallBack.onSuccess(goodsLvTwoBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
