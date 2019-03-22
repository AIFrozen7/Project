package com.bwei.weidustore.api;


import com.bwei.weidustore.bean.AddShopCar;
import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.bean.PersonBean;
import com.bwei.weidustore.bean.RegistBean;
import com.bwei.weidustore.bean.ShopCarBean;
import com.bwei.weidustore.bean.ShopListBean;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @Auther: 李
 * @Date: 2019/2/26 20:34:43
 * @Description:
 */
public interface IApi {
    //首页
    @GET(Api.SHOPLIST)
    Observable<ShopListBean> getShopList();
    //首页轮播
    @GET(Api.BANNER)
    Observable<BannerBean> getBannerData();
    //商品详情
    @GET(Api.SHOP_DETAILS)
    Observable<DetailsBean> getGoodsDetails(@Query("commodityId")int commodityId);
    //评论
    @GET(Api.COMMENTLIST)
    Observable<CommetListBean> getComment(@Query("commodityId")int commodityId);
    //登录
    @POST(Api.LOGIN)
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("phone")String phone,@Field("pwd")String pwd);
    //注册
    @POST(Api.REGIST)
    @FormUrlEncoded
    Observable<RegistBean> getRegistData(@Field("phone")String phone,@Field("pwd")String pwd);
    //查询用户信息
    @GET(Api.SELECTBYID)
    Observable<PersonBean> getPersonMsg(@HeaderMap Map<String, String> map);
    //查询购物车
    @GET(Api.SHOPCARLIST)
    Observable<ShopCarBean> getShopCar(@HeaderMap Map<String, String> map);
    //同步购物车
    @PUT(Api.ADDSHAOPCAR)
    Observable<AddShopCar> addShopCar(@HeaderMap Map<String, String> map, @Query("data")String data);



}
