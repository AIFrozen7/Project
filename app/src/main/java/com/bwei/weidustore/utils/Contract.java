package com.bwei.weidustore.utils;

/**
 * @Auther: 李
 * @Date: 2019/3/20 10:04:06
 * @Description:
 */
public interface Contract {
    interface IHomeModel{
        void getHomeData(IHomeModel.IHomeCallBack iHomeCallBack);
        interface IHomeCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IHomePresenter{
        void getData();
    }
    interface IHomeView{
        void getData(Object o);
    }
}
