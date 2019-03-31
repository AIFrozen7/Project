package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.PayBillModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/29 10:48:28
 * @Description:
 */
public class PayBillPresenter extends BasePresenter<PayBillModel> implements Contract.IPayBillPresenter {
    Contract.IPayBillView iPayBillView;
    private final PayBillModel payBillModel;

    public PayBillPresenter(Contract.IPayBillView iPayBillView) {
        this.iPayBillView = iPayBillView;
        payBillModel = new PayBillModel();
    }

    @Override
    public void payBill(Map<String, String> map, String orderId, int payType) {
        payBillModel.payBill(map, orderId, payType, new Contract.IPayBillModel.IPayBillCallBack() {
            @Override
            public void onSuccess(Object o) {
                iPayBillView.payBill(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
