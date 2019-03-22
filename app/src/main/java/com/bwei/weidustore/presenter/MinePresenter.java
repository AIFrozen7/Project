package com.bwei.weidustore.presenter;

import com.bwei.weidustore.base.BasePresenter;
import com.bwei.weidustore.model.MineModel;
import com.bwei.weidustore.utils.Contract;

import java.util.Map;

/**
 * @Auther: 李
 * @Date: 2019/3/21 11:40:48
 * @Description:
 */
public class MinePresenter extends BasePresenter<MineModel> implements Contract.IMinePresenter {
    Contract.IMineView iMineView;
    private MineModel mineModel;

    public MinePresenter(Contract.IMineView iMineView) {
        this.iMineView = iMineView;
        mineModel = new MineModel();
    }
    //根据ID查询用户信息
    @Override
    public void getPersonDataById(Map<String, String> map) {
        mineModel.getMineData(map, new Contract.IMineModel.IMineCallBack() {
            @Override
            public void onSuccess(Object o) {
                iMineView.getMineData(o);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
