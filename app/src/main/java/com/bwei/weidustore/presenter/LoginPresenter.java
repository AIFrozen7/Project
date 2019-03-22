package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.LoginModel;
import com.bwei.weidustore.utils.Contract;

/**
 * @Auther: 李
 * @Date: 2019/3/21 11:40:38
 * @Description:
 */
public class LoginPresenter extends BasePresenter<LoginModel> implements Contract.ILoginPresenter {
    Contract.ILoginView iLoginView;
    private LoginModel loginModel;

    public LoginPresenter(Contract.ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        loginModel = new LoginModel();
    }
    //登录
    @Override
    public void getLogin(String phone, String pwd) {
        loginModel.getLoginData(phone, pwd, new Contract.ILoginModel.ILoginCallBack() {
            @Override
            public void onSuccess(Object o) {
                iLoginView.getLoginData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
