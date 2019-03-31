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
    public static final String SHOP_DETAILS = "small/commodity/v1/findCommodityDetailsById";
    //圈子列表
    public static final String  SHOP_CIRCLE = "small/circle/v1/findCircleList?count=5&page=";
    //搜索
    public static final String SEARCH = "small/commodity/v1/findCommodityByKeyword?count=10";
    //用户信息
    public static final String USER =  "small/user/verify/v1/getUserById";
    //更多
    public static final String MORE =  "small/commodity/v1/findCommodityListByLabel?page=1&count=10&labelId=";
    //商品评论
    public static final String COMMENTLIST =  "small/commodity/v1/CommodityCommentList?count=10&page=1";
    //同步购物车(加入购物车)
    public static final String ADDSHAOPCAR = "small/order/verify/v1/syncShoppingCart";
    //查询购物车
    public static final String SHOPCARLIST =  "small/order/verify/v1/findShoppingCart";
    //.根据用户ID查询用户信息
    public static final String SELECTBYID  =  "small/user/verify/v1/getUserById";
    //查询收货地址
    public static final String SELECTADDRESS = "small/user/verify/v1/receiveAddressList";
    //新增收货地址
    public static final String ADDADDRESS = "small/user/verify/v1/addReceiveAddress";
    //设置默认收货地址
    public static final String DEFAULTADDRESS = "small/user/verify/v1/setDefaultReceiveAddress";
    //创建订单
    public static final String CREATEBILL = "small/order/verify/v1/createOrder";
    //支付订单
    public static final String PAYBILL = "small/order/verify/v1/pay";
    //根据订单状态查询订单信息
    public static final String SELECTBILL = "small/order/verify/v1/findOrderListByStatus?page=1&count=20";
    //查询一级商品类目
    public static final String SELECT_LV_ONE = "small/commodity/v1/findFirstCategory";
    //查询二级商品类目
    public static final String SELECT_LV_TWO = "small/commodity/v1/findSecondCategory";
    //查询三级商品类目
    public static final String SELECT_LV_THREE = "small/commodity/v1/findCommodityByCategory?count=10";
    //收货
    public static final String RECEIPT = "small/order/verify/v1/confirmReceipt";
}
