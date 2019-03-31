package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.AddAddressModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/27 21:05:08
 * @Description:
 */
public class AddAddressPresenter extends BasePresenter<AddAddressModel> implements Contract.IAddAddressPresenter {
    Contract.IAddAddressView iAddAddressView;
    private final AddAddressModel addAddressModel;

    public AddAddressPresenter(Contract.IAddAddressView iAddAddressView) {
        this.iAddAddressView = iAddAddressView;
        addAddressModel = new AddAddressModel();
    }

    @Override
    public void AddAddressData(Map<String, String> map, String realName, String phone, String address, String zipCode) {
        addAddressModel.AddAddressData(map, realName, phone, address, zipCode, new Contract.IAddAddressModel.IAddAddressCallBack() {
            @Override
            public void onSuccess(Object o) {
                iAddAddressView.AddAddressData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
