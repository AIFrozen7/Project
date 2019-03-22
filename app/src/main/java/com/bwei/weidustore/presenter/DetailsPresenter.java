package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.DetailsModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Auther: 李
 * @Date: 2019/3/21 11:39:39
 * @Description:
 */
public class DetailsPresenter extends BasePresenter<DetailsModel> implements Contract.IDetailsPresenter {
    Contract.IDetailsView iDetailsView;
    private  DetailsModel detailsModel;

    public DetailsPresenter(Contract.IDetailsView iDetailsView) {
        this.iDetailsView = iDetailsView;
        detailsModel = new DetailsModel();
    }

    //商品详情信息
    @Override
    public void getData(int commodityId) {
        detailsModel.getData(commodityId, new Contract.IDetailsModel.IDetailsCallBack() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.getData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //详情(评论)
    @Override
    public void getCommentData(int commodityId) {
        detailsModel.getCommentData(commodityId, new Contract.IDetailsModel.IDetailsCallBack2() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.getCommentData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    //同步购物车
    @Override
    public void addShopCarData(Map<String, String> map, String data) {
        detailsModel.addShopCarData(map, data, new Contract.IDetailsModel.IDetailsCallBack3() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.addShopCarData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    //查询购物车
    @Override
    public void getShopCarData(Map<String, String> map) {
        detailsModel.getShopCarData(map, new Contract.IDetailsModel.IDetailsCallBack4() {
            @Override
            public void onSuccess(Object o) {
                iDetailsView.getShopCarData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }


}
