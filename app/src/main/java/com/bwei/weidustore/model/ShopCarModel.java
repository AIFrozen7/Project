package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther: 李
 * @Date: 2019/3/22 13:49:50
 * @Description:
 */
public class ShopCarModel implements Contract.IShopCarModel {
    @Override
    public void getShopCarData(Map<String, String> map, final IShopCarCallBack iShopCarCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<ShopCarBean> shopCar = iApi.getShopCar(map);
        shopCar.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShopCarBean shopCarBean) {
                        iShopCarCallBack.onSuccess(shopCarBean);
                    }
                });
    }
}
