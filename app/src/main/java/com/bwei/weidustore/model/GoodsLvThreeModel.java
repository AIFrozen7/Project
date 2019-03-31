package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.GoodsLvThreeBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/30 15:05:06
 * @Description:
 */
public class GoodsLvThreeModel implements Contract.IGoodsLvThreeModel {
    @Override
    public void getGoodsThreeData(String categoryId, int page, final IGoodsThreeCallBack iGoodsThreeCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<GoodsLvThreeBean> lvThreeData = iApi.getLvThreeData(categoryId, page);
        lvThreeData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<GoodsLvThreeBean>() {
                    @Override
                    public void onNext(GoodsLvThreeBean goodsLvThreeBean) {
                        iGoodsThreeCallBack.onSuccess(goodsLvThreeBean);
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
