package com.bwei.weidustore.api;

/**
 * @Auther: 李
 * @Date: 2019/2/13 09:23:00
 * @Description:
 */
public class Api {
    public static final String BASE_URL="http://172.17.8.100/";
    //商品列表
    public static final String SHOPLIST="small/commodity/v1/commodityList";
    //登录
    public static final String LOGIN="small/user/v1/login";
    //轮播
    public static final String BANNER = "small/commodity/v1/bannerShow";
    //注册
    public static final String REGIST = "small/user/v1/register";
    //商品详情
    public static final String SHOP_DETAILS = "small/commodity/v1/findCommodityDetailsById?commodityId=";
    //圈子列表
    public static final String  SHOP_CIRCLE = "small/circle/v1/findCircleList?count=5&page=";
    //搜索
    public static final String SEARCH = "small/commodity/v1/findCommodityByKeyword?page=1&count=10&keyword=";
    //用户信息
    public static final String USER =  "small/user/verify/v1/getUserById";
    //更多
    public static final String MORE =  "small/commodity/v1/findCommodityListByLabel?page=1&count=10&labelId=";
    //商品评论
    public static final String COMMENTLIST =  "small/commodity/v1/CommodityCommentList?count=10&page=";
    //同步购物车(加入购物车)
    public static final String ADDSHAOPCAR = "small/order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String SHOPCARLIST =  "small/order/verify/v1/findShoppingCart";
    //.根据用户ID查询用户信息
    public static final String SELECTBYID =  "small/user/verify/v1/getUserById";
}
