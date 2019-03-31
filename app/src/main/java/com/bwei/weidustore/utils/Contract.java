package com.bwei.weidustore.utils;

import java.util.Map;

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
        void getLvOneData(IHomeCallBack iHomeCallBack);
        void getLvTwoData(String firstCategoryId,IHomeCallBack iHomeCallBack);
        interface IHomeCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IHomePresenter{
        void getShopList();
        void getBannerData();
        void getLvOneData();
        void getLvTwoData(String firstCategoryId);
    }
    interface IHomeView{
        void getData(Object o);
        void getBannerData(Object o);
        void getLvOneData(Object o);
        void getLvTwoData(Object o);
    }
    /**
     * 三级类目
     */
    interface IGoodsLvThreeModel{
        void getGoodsThreeData(String categoryId,int page,IGoodsThreeCallBack iGoodsThreeCallBack);
        interface IGoodsThreeCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IGoodsLvThreePresenter{
        void getGoodsThreeData(String categoryId,int page);
    }
    interface IGoodsLvThreeView{
        void getGoodsThreeData(Object o);
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
    /**
     * 查询收货地址以及设置默认收货地址
     */
    interface IAddressModel{
        void getAddressData(Map<String,String> map,ISelectAddressCallBack iSelectAddressCallBack);
        void setDefaultAddress(Map<String,String> map,int id,ISetAddressCallBack iSetAddressCallBack);
        interface ISelectAddressCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
        interface ISetAddressCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IAddressPresenter{
        void getAddressData(Map<String,String> map);
        void setAddressData(Map<String,String> map,int id);
    }
    interface IAddressView{
        void getAddressData(Object o);
        void getDefaultAddressData(Object o);
    }
    /**
     * 新建收货地址
     */
    interface IAddAddressModel{
        void AddAddressData(Map<String,String> map,String realName,String phone,String address,String zipCode,IAddAddressCallBack iAddAddressCallBack);
        interface IAddAddressCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }

    }
    interface IAddAddressPresenter{
        void AddAddressData(Map<String,String> map,String realName,String phone,String address,String zipCode);
    }
    interface IAddAddressView{
        void AddAddressData(Object o);
    }
    /**
     * 创建订单(含有查询地址)
     */
    interface ICreatteBillModel{
        void getCreateAddressData(Map<String,String> map,ISelectCreateAddressCallBack iSelectCreateAddressCallBack);
        void createBill(Map<String,String> map,String orderInfo,double totalPrice,int addressId,ICreateBillCallBack iCreateBillCallBack);
        interface ISelectCreateAddressCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
        interface ICreateBillCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface ICreateBillPresenter{
        void getCreateAddressData(Map<String,String> map);
        void createBill(Map<String,String> map,String orderInfo,double totalPrice,int addressId);
    }
    interface ICreateBillView{
        void getCreateAddressData(Object o);
        void createBill(Object o);
    }
    /**
     * 支付订单
     */
    interface IPayBillModel{
        void payBill(Map<String,String> map,String orderId,int payType,IPayBillCallBack iPayBillCallBack);
        interface IPayBillCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface IPayBillPresenter{
        void payBill(Map<String,String> map,String orderId,int payType);
    }
    interface IPayBillView{
        void payBill(Object o);
    }

    /**
     * 根据订单状态查询订单
     */
    interface ISelectBillModel{
        void selectBill(Map<String, String> map,int status,ISelectBillCallBack iSelectBillCallBack);
        void receiptData(Map<String, String> map,String orderId,IReceiptCallBack iReceiptCallBack);
        interface ISelectBillCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
        interface IReceiptCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface ISelectBillPresenter{
        void selectBill(Map<String, String> map,int status);
        void receiptData(Map<String, String> map,String orderId);
    }
    interface ISelectBillView{
        void selectBill(Object o);
        void receiptData(Object o);
    }
    /**
     * 搜索
     */
    interface ISearchModel{
        void getSearchData(int page,String keyword,ISearchDataCallBack iSearchDataCallBack);
        interface ISearchDataCallBack{
            void onSuccess(Object o);
            void onFailed(Exception e);
        }
    }
    interface ISearchPresenter{
        void getSearchData(int page,String keyword);
    }
    interface ISearchView{
        void getSearchData(Object o);
    }
}
