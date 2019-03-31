package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.SelectBillModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/29 14:26:30
 * @Description:
 */
public class SelectBillPresenter extends BasePresenter<SelectBillModel> implements Contract.ISelectBillPresenter {
    Contract.ISelectBillView iSelectBillView;
    private final SelectBillModel selectBillModel;

    public SelectBillPresenter(Contract.ISelectBillView iSelectBillView) {
        this.iSelectBillView = iSelectBillView;
        selectBillModel = new SelectBillModel();
    }

    @Override
    public void selectBill(Map<String, String> map, int status) {
        selectBillModel.selectBill(map, status, new Contract.ISelectBillModel.ISelectBillCallBack() {
            @Override
            public void onSuccess(Object o) {
                iSelectBillView.selectBill(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void receiptData(Map<String, String> map, String orderId) {
        selectBillModel.receiptData(map, orderId, new Contract.ISelectBillModel.IReceiptCallBack() {
            @Override
            public void onSuccess(Object o) {
                iSelectBillView.receiptData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
