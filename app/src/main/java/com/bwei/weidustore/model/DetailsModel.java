package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther: 李
 * @Date: 2019/3/20 14:47:30
 * @Description:
 */
public class DetailsModel implements Contract.IDetailsModel {
    @Override
    public void getData(int commodityId, final IDetailsCallBack iDetailsCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<DetailsBean> goodsDetails = iApi.getGoodsDetails(commodityId);
        goodsDetails.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<DetailsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DetailsBean detailsBean) {
                        iDetailsCallBack.onSuccess(detailsBean);
                    }
                });
    }

    @Override
    public void getCommentData(int commodityId, final IDetailsCallBack2 iDetailsCallBack2) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<CommetListBean> comment = iApi.getComment(commodityId);
        comment.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<CommetListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(CommetListBean commetListBean) {
                        iDetailsCallBack2.onSuccess(commetListBean);
                    }
                });
    }

    @Override
    public void addShopCarData(Map<String, String> map, RequestBody body, IDetailsCallBack3 iDetailsCallBack3) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
    }
}
