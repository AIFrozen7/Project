package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.RegistModel;
import com.bwei.weidustore.utils.Contract;

/**
 * @Auther: 李
 * @Date: 2019/3/21 11:40:56
 * @Description:
 */
public class RegistPresenter extends BasePresenter<RegistModel> implements Contract.IRegistPresenter {
    Contract.IRegistView iRegistView;
    private RegistModel registModel;

    public RegistPresenter(Contract.IRegistView iRegistView) {
        this.iRegistView = iRegistView;
        registModel = new RegistModel();
    }
    //注册
    @Override
    public void getRegist(String phone, String pwd) {
        registModel.getRegistData(phone, pwd, new Contract.IRegistModel.IRegistCallBack() {
            @Override
            public void onSuccess(Object o) {
                iRegistView.getRegistData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

}
