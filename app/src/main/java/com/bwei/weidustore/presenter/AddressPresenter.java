package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.AddressModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/27 20:01:39
 * @Description:
 */
public class AddressPresenter extends BasePresenter<AddressModel> implements Contract.IAddressPresenter {
    Contract.IAddressView iAddressView;
    private  AddressModel addressModel;

    public AddressPresenter(Contract.IAddressView iAddressView) {
        this.iAddressView = iAddressView;
        addressModel = new AddressModel();
    }

    @Override
    public void getAddressData(Map<String, String> map) {
        addressModel.getAddressData(map, new Contract.IAddressModel.ISelectAddressCallBack() {
            @Override
            public void onSuccess(Object o) {
                iAddressView.getAddressData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void setAddressData(Map<String, String> map, int id) {
        addressModel.setDefaultAddress(map, id, new Contract.IAddressModel.ISetAddressCallBack() {
            @Override
            public void onSuccess(Object o) {
                iAddressView.getDefaultAddressData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }


}
