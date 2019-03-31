package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.CreateBillModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/28 21:28:22
 * @Description:
 */
public class CreateBillPresenter extends BasePresenter<CreateBillModel> implements Contract.ICreateBillPresenter {
    Contract.ICreateBillView iCreateBillView;
    private final CreateBillModel createBillModel;

    public CreateBillPresenter(Contract.ICreateBillView iCreateBillView) {
        this.iCreateBillView = iCreateBillView;
        createBillModel = new CreateBillModel();
    }

    @Override
    public void getCreateAddressData(Map<String, String> map) {
        createBillModel.getCreateAddressData(map, new Contract.ICreatteBillModel.ISelectCreateAddressCallBack() {
            @Override
            public void onSuccess(Object o) {
                iCreateBillView.getCreateAddressData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void createBill(Map<String, String> map, String orderInfo, double totalPrice, int addressId) {
        createBillModel.createBill(map, orderInfo, totalPrice, addressId, new Contract.ICreatteBillModel.ICreateBillCallBack() {
            @Override
            public void onSuccess(Object o) {
                iCreateBillView.createBill(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
