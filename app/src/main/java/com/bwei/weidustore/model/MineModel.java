package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.bean.PersonBean;
import com.bwei.weidustore.utils.Contract;
import com.bwei.weidustore.utils.RetrofitManager;

import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @Auther: 李
 * @Date: 2019/3/20 20:52:26
 * @Description:
 */
public class MineModel implements Contract.IMineModel {
    @Override
    public void getMineData(Map<String, String> map, final IMineCallBack iMineCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Observable<PersonBean> personMsg = iApi.getPersonMsg(map);
        personMsg.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<PersonBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PersonBean personBean) {
                        iMineCallBack.onSuccess(personBean);
                    }
                });
    }
}
