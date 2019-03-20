package com.bwei.weidustore.utils;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Auther: 李
 * @Date: 2019/3/20 10:04:06
 * 契约类
 * @Description:
 */
public interface Contract {
    /**
     * 首页
     */
    interface IHomeModel{
        void getHomeData(IHomeModel.IHomeCallBack iHomeCallBack);
        void getBannerData(IHomeCallBack iHomeCallBack);
        interface IHomeCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IHomeView{
        void getData(Object o);
        void getBannerData(Object o);
    }
    /**
     * 详情
     */
    interface IDetailsModel {
        void getData(int commodityId,IDetailsCallBack iDetailsCallBack);
        void getCommentData(int commodityId,IDetailsCallBack2 iDetailsCallBack2);
        void addShopCarData(Map<String,String> map, RequestBody body, IDetailsCallBack3 iDetailsCallBack3);

        interface IDetailsCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
        interface IDetailsCallBack2{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
        interface IDetailsCallBack3{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IDetailsView {
        void getData(Object o);
        void getCommentData(Object o);
        void addShopCarData(Object o);
    }
    /**
     * 个人中心
     */
    interface IMineModel{
        void getLoginData(String phone,String pwd,ILoginCallBack iLoginCallBack);
        interface ILoginCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IMineView{
        void getLoginData(Object o);
    }
}
