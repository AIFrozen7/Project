package com.bwei.weidustore.api;


import com.bwei.weidustore.bean.AddAddress;
import com.bwei.weidustore.bean.AddShopCar;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.CreateBillBean;
import com.bwei.weidustore.bean.DefaultAddressBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.bean.GoodsLvOneBean;
import com.bwei.weidustore.bean.GoodsLvThreeBean;
import com.bwei.weidustore.bean.GoodsLvTwoBean;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.bean.PayBillBean;
import com.bwei.weidustore.bean.PersonBean;
import com.bwei.weidustore.bean.ReceiptBean;
import com.bwei.weidustore.bean.RegistBean;
import com.bwei.weidustore.bean.SearchBean;
import com.bwei.weidustore.bean.SelectAddressBean;
import com.bwei.weidustore.bean.SelectBillBean;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.bean.ShopListBean;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * @Auther: 李
 * @Date: 2019/2/26 20:34:43
 * @Description:
 */
public interface IApi {
    //首页
    @GET(Api.SHOPLIST)
    Flowable<ShopListBean> getShopList();
    //首页轮播
    @GET(Api.BANNER)
    Flowable<BannerBean> getBannerData();
    //商品详情
    @GET(Api.SHOP_DETAILS)
    Flowable<DetailsBean> getGoodsDetails(@Query("commodityId")int commodityId);
    //评论
    @GET(Api.COMMENTLIST)
    Flowable<CommetListBean> getComment(@Query("commodityId")int commodityId);
    //登录
    @POST(Api.LOGIN)
    @FormUrlEncoded
    Flowable<LoginBean> getLoginData(@Field("phone")String phone,@Field("pwd")String pwd);
    //注册
    @POST(Api.REGIST)
    @FormUrlEncoded
    Flowable<RegistBean> getRegistData(@Field("phone")String phone,@Field("pwd")String pwd);
    //查询用户信息
    @GET(Api.SELECTBYID)
    Flowable<PersonBean> getPersonMsg(@HeaderMap Map<String, String> map);
    //查询购物车
    @GET(Api.SHOPCARLIST)
    Flowable<ShopCarBean> getShopCar(@HeaderMap Map<String, String> map);
    //同步购物车
    @PUT(Api.ADDSHAOPCAR)
    Flowable<AddShopCar> addShopCar(@HeaderMap Map<String, String> map, @Query("data")String data);
    //查询收货地址
    @GET(Api.SELECTADDRESS)
    Flowable<SelectAddressBean> selectAddress(@HeaderMap Map<String, String> map);
    //新增收货地址
    @POST(Api.ADDADDRESS)
    @FormUrlEncoded
    Flowable<AddAddress> addAddress(@HeaderMap Map<String, String> map,@Field("realName")String realName,@Field("phone")String phone,@Field("address")String address,@Field("zipCode")String zipCode);
    //设置默认收货地址
    @POST(Api.DEFAULTADDRESS)
    @FormUrlEncoded
    Flowable<DefaultAddressBean> setDefaultAddress(@HeaderMap Map<String, String> map,@Field("id")int id);
    //创建订单
    @POST(Api.CREATEBILL)
    @FormUrlEncoded
    Flowable<CreateBillBean> createBill(@HeaderMap Map<String, String> map,@Query("orderInfo")String orderInfo,@Field("totalPrice")double totalPrice,@Field("addressId")int addressId);
    //支付订单
    @POST(Api.PAYBILL)
    @FormUrlEncoded
    Flowable<PayBillBean> payBill(@HeaderMap Map<String, String> map,@Field("orderId") String orderId,@Field("payType")int payType);
    //根据订单状态查询订单信息
    @GET(Api.SELECTBILL)
    Flowable<SelectBillBean> selectBill(@HeaderMap Map<String, String> map,@Query("status")int status);
    //搜索
    @GET(Api.SEARCH)
    Flowable<SearchBean> searchData(@Query("keyword")String keyword,@Query("page")int page);
    //查询一级商品类目
    @GET(Api.SELECT_LV_ONE)
    Flowable<GoodsLvOneBean> getLvOneData();
    //查询二级商品类目
    @GET(Api.SELECT_LV_TWO)
    Flowable<GoodsLvTwoBean> getLvTwoData(@Query("firstCategoryId")String firstCategoryId);
    //查询三级商品类目
    @GET(Api.SELECT_LV_THREE)
    Flowable<GoodsLvThreeBean> getLvThreeData(@Query("categoryId")String categoryId,@Query("page")int page);
    //收货
    @PUT(Api.RECEIPT)
    Flowable<ReceiptBean> receiptData(@HeaderMap Map<String, String> map,@Query("orderId")String orderId);
}
