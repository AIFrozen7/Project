package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.CreateBillBean;
import com.bwei.weidustore.bean.SelectAddressBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/28 21:26:58
 * @Description:
 */
public class CreateBillModel implements Contract.ICreatteBillModel {
    @Override
    public void getCreateAddressData(Map<String, String> map, final ISelectCreateAddressCallBack iSelectCreateAddressCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<SelectAddressBean> selectAddress = iApi.selectAddress(map);
        selectAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<SelectAddressBean>() {
                    @Override
                    public void onNext(SelectAddressBean selectAddressBean) {
                        iSelectCreateAddressCallBack.onSuccess(selectAddressBean);
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
    public void createBill(Map<String, String> map, String orderInfo, double totalPrice, int addressId, final ICreateBillCallBack iCreateBillCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<CreateBillBean> bill = iApi.createBill(map, orderInfo, totalPrice, addressId);
        bill.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<CreateBillBean>() {
                    @Override
                    public void onNext(CreateBillBean createBillBean) {
                        iCreateBillCallBack.onSuccess(createBillBean);
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
