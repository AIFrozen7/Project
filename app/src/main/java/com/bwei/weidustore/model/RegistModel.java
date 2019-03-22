package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.RegistBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther: 李
 * @Date: 2019/3/21 10:49:25
 * @Description:
 */
public class RegistModel implements Contract.IRegistModel {
    @Override
    public void getRegistData(String phone, String pwd, final IRegistCallBack iRegistCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<RegistBean> registData = iApi.getRegistData(phone, pwd);
        registData.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RegistBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RegistBean registBean) {
                        iRegistCallBack.onSuccess(registBean);
                    }
                });
    }
}
