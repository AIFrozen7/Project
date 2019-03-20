package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.ShopListBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:05:08
 * @Description:
 */
public class HomeModel implements Contract.IHomeModel {

    @Override
    public void getHomeData(final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<ShopListBean> shopList = iApi.getShopList();
        shopList.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ShopListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShopListBean shopListBean) {
                        iHomeCallBack.onSuccess(shopListBean);
                    }
                });
    }

    @Override
    public void getBannerData(final IHomeCallBack iHomeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<BannerBean> bannerData = iApi.getBannerData();
        bannerData.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        iHomeCallBack.onSuccess(bannerBean);
                    }
                });
    }
}
