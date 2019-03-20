package com.bwei.weidustore.api;


import com.bwei.weidustore.bean.ShopListBean;

import retrofit2.http.GET;
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

//    @GET
//    Observable<MoreBean> getMoreData(@Url String url);
//    //评论
//    @GET
//    Observable<CommetListBean> getComment(@Url String url);
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
