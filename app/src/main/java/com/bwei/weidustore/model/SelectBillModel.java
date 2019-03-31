package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.ReceiptBean;
import com.bwei.weidustore.bean.SelectBillBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/29 14:26:16
 * @Description:
 */
public class SelectBillModel implements Contract.ISelectBillModel {
    @Override
    public void selectBill(Map<String, String> map, int status, final ISelectBillCallBack iSelectBillCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<SelectBillBean> selectBill = iApi.selectBill(map, status);
        selectBill.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<SelectBillBean>() {
                    @Override
                    public void onNext(SelectBillBean selectBillBean) {
                        iSelectBillCallBack.onSuccess(selectBillBean);
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
    public void receiptData(Map<String, String> map, String orderId, final IReceiptCallBack iReceiptCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<ReceiptBean> receiptData = iApi.receiptData(map, orderId);
        receiptData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<ReceiptBean>() {
                    @Override
                    public void onNext(ReceiptBean receiptBean) {
                        iReceiptCallBack.onSuccess(receiptBean);
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
