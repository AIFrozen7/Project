package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.ShopCarModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/22 13:50:03
 * @Description:
 */
public class ShopCarPresenter extends BasePresenter<ShopCarModel> implements Contract.IShopCarPresenter {
    Contract.IShopCarView iShopCarView;
    private final ShopCarModel shopCarModel;

    public ShopCarPresenter(Contract.IShopCarView iShopCarView) {
        this.iShopCarView = iShopCarView;
        shopCarModel = new ShopCarModel();
    }

    @Override
    public void getShopCarDataById(final Map<String, String> map) {
        shopCarModel.getShopCarData(map, new Contract.IShopCarModel.IShopCarCallBack() {
            @Override
            public void onSuccess(Object o) {
                iShopCarView.getShopCarData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
