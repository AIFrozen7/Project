package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.AddAddress;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @Auther: 李
 * @Date: 2019/3/27 21:03:24
 * @Description:
 */
public class AddAddressModel implements Contract.IAddAddressModel {
    @Override
    public void AddAddressData(Map<String, String> map, String realName, String phone, String address, String zipCode, final IAddAddressCallBack iAddAddressCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<AddAddress> addAddress = iApi.addAddress(map, realName, phone, address, zipCode);
        addAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<AddAddress>() {
                    @Override
                    public void onNext(AddAddress addAddress) {
                        iAddAddressCallBack.onSuccess(addAddress);
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
