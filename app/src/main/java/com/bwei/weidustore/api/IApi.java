package com.bwei.weidustore.api;


import com.bwei.weidustore.bean.BannerBean;
import com.bwei.weidustore.bean.CommetListBean;
import com.bwei.weidustore.bean.DetailsBean;
import com.bwei.weidustore.bean.LoginBean;
import com.bwei.weidustore.bean.ShopListBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
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
    @POST(Api.LOGIN)
    @FormUrlEncoded
    Observable<LoginBean> getLoginData(@Field("phone")String phone,@Field("pwd")String pwd);
//    @GET
//    Observable<MoreBean> getMoreData(@Url String url);

//    //查询购物车
//    @GET
//    Observable<ShopCarBean> getShopCar(@HeaderMap Map<String, String> map, @Url String url);
//
//    //同步购物车
//    @Multipart
//    @PUT
//    Observable<AddShopCar> addShopCar(@HeaderMap Map<String, String> map, @Part RequestBody body, @Url String url);
//
//    //查询用户信息
//    @GET
//    Observable<PersonBean> getPersonMsg(@HeaderMap Map<String, String> map, @Url String url);
}
