package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.HomeModel;
import com.bwei.weidustore.utils.Contract;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:05:16
 * @Description:
 */
public class HomePresenter extends BasePresenter<HomeModel> {
    Contract.IHomeView iHomeView;
    private final HomeModel homeModel;

    public HomePresenter(Contract.IHomeView iHomeView) {
        this.iHomeView = iHomeView;
        homeModel = new HomeModel();
    }

    @Override
    public void getShopList() {
        homeModel.getHomeData(new Contract.IHomeModel.IHomeCallBack() {
            @Override
            public void onSuccess(Object o) {
                iHomeView.getData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
