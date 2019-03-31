package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.GoodsLvThreeModel;
import com.bwei.weidustore.utils.Contract;

/**
 * @Auther: 李
 * @Date: 2019/3/30 15:05:16
 * @Description:
 */
public class GoodsLvThreePresenter extends BasePresenter<GoodsLvThreeModel> implements Contract.IGoodsLvThreePresenter {
    Contract.IGoodsLvThreeView iGoodsLvThreeView;
    private final GoodsLvThreeModel goodsLvThreeModel;

    public GoodsLvThreePresenter(Contract.IGoodsLvThreeView iGoodsLvThreeView) {
        this.iGoodsLvThreeView = iGoodsLvThreeView;
        goodsLvThreeModel = new GoodsLvThreeModel();
    }

    @Override
    public void getGoodsThreeData(String categoryId, int page) {
        goodsLvThreeModel.getGoodsThreeData(categoryId, page, new Contract.IGoodsLvThreeModel.IGoodsThreeCallBack() {
            @Override
            public void onSuccess(Object o) {
                iGoodsLvThreeView.getGoodsThreeData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
