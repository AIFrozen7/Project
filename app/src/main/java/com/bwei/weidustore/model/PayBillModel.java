package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.PayBillBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/29 10:48:18
 * @Description:
 */
public class PayBillModel implements Contract.IPayBillModel {
    @Override
    public void payBill(Map<String, String> map, String orderId, int payType, final IPayBillCallBack iPayBillCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        final Flowable<PayBillBean> payBill = iApi.payBill(map, orderId, payType);
        payBill.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<PayBillBean>() {
                    @Override
                    public void onNext(PayBillBean payBillBean) {
                        iPayBillCallBack.onSuccess(payBillBean);
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
