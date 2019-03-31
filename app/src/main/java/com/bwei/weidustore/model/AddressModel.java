package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.AddAddress;
import com.bwei.weidustore.bean.DefaultAddressBean;
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
 * @Date: 2019/3/27 20:01:28
 * @Description:
 */
public class AddressModel implements Contract.IAddressModel {
    @Override
    public void getAddressData(Map<String, String> map, final ISelectAddressCallBack iSelectAddressCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<SelectAddressBean> selectAddress = iApi.selectAddress(map);
        selectAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<SelectAddressBean>() {
                    @Override
                    public void onNext(SelectAddressBean selectAddressBean) {
                        iSelectAddressCallBack.onSuccess(selectAddressBean);
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
    public void setDefaultAddress(Map<String, String> map, int id, final ISetAddressCallBack iSetAddressCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<DefaultAddressBean> setDefaultAddress = iApi.setDefaultAddress(map, id);
        setDefaultAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<DefaultAddressBean>() {
                    @Override
                    public void onNext(DefaultAddressBean defaultAddressBean) {
                        iSetAddressCallBack.onSuccess(defaultAddressBean);
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
