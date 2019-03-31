package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.AddShopCar;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/20 14:47:30
 * @Description:
 */
public class DetailsModel implements Contract.IDetailsModel {
    @Override
    public void getData(int commodityId, final IDetailsCallBack iDetailsCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<DetailsBean> goodsDetails = iApi.getGoodsDetails(commodityId);

        goodsDetails.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<DetailsBean>() {
                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        iDetailsCallBack.onSuccess(detailsBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getCommentData(int commodityId, final IDetailsCallBack2 iDetailsCallBack2) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<CommetListBean> comment = iApi.getComment(commodityId);
        comment.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<CommetListBean>() {
                    @Override
                    public void onNext(CommetListBean commetListBean) {
                        iDetailsCallBack2.onSuccess(commetListBean);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void addShopCarData(Map<String, String> map, String data, final IDetailsCallBack3 iDetailsCallBack3) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<AddShopCar> addShopCarObservable = iApi.addShopCar(map, data);
        addShopCarObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<AddShopCar>() {
                    @Override
                    public void onNext(AddShopCar addShopCar) {
                        iDetailsCallBack3.onSuccess(addShopCar);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getShopCarData(Map<String, String> map, final IDetailsCallBack4 iDetailsCallBack4) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<ShopCarBean> shopCar = iApi.getShopCar(map);
        shopCar.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ShopCarBean>() {
                    @Override
                    public void onNext(ShopCarBean shopCarBean) {
                        iDetailsCallBack4.onSuccess(shopCarBean);
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
