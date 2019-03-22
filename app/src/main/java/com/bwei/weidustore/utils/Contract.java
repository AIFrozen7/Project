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
    interface IHomePresenter{
        void getShopList();
        void getBannerData();
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
        void addShopCarData(Map<String,String> map, String data, IDetailsCallBack3 iDetailsCallBack3);
        void getShopCarData(Map<String,String> map,IDetailsCallBack4 iDetailsCallBack4);

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
        interface IDetailsCallBack4{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IDetailsPresenter{
        void getData(int commodityId);
        void getCommentData(int commodityId);
        void addShopCarData(Map<String,String> map, String data);
        void getShopCarData(Map<String,String> map);
    }
    interface IDetailsView {
        void getData(Object o);
        void getCommentData(Object o);
        void addShopCarData(Object o);
        void getShopCarData(Object o);
    }
    /**
     * 登录
     */
    interface ILoginModel{
        void getLoginData(String phone,String pwd,ILoginCallBack iLoginCallBack);
        interface ILoginCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface ILoginPresenter{
        void getLogin(String phone,String pwd);
    }
    interface ILoginView{
        void getLoginData(Object o);
    }
    /**
     * 注册
     */
    interface IRegistModel{
        void getRegistData(String phone,String pwd,IRegistCallBack iRegistCallBack);
        interface IRegistCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IRegistPresenter{
        void getRegist(String phone,String pwd);
    }
    interface IRegistView{
        void getRegistData(Object o);
    }
    /**
     * 个人中心
     */
    interface IMineModel{
        void getMineData(Map<String,String>map,IMineCallBack iMineCallBack);
        interface IMineCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IMinePresenter{
        void getPersonDataById(Map<String,String>map);
    }
    interface IMineView{
        void getMineData(Object o);
    }
    /**
     * 购物
     */
    interface IShopCarModel{
        void getShopCarData(Map<String,String> map, IShopCarCallBack iShopCarCallBack);
        interface IShopCarCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IShopCarPresenter{
        void getShopCarDataById(Map<String,String>map);
    }
    interface IShopCarView{
        void getShopCarData(Object o);
    }
}
