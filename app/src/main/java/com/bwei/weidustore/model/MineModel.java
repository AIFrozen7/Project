package com.bwei.weidustore.model;

import com.bwei.weidustore.api.IApi;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.bean.PersonBean;
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
 * @Date: 2019/3/20 20:52:26
 * @Description:
 */
public class MineModel implements Contract.IMineModel {
    @Override
    public void getMineData(Map<String, String> map, final IMineCallBack iMineCallBack) {
        IApi iApi = RetrofitManager.getRetrofitInstance().setCreat(IApi.class);
        Flowable<PersonBean> personMsg = iApi.getPersonMsg(map);
        personMsg.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<PersonBean>() {
                    @Override
                    public void onNext(PersonBean personBean) {
                        iMineCallBack.onSuccess(personBean);
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
