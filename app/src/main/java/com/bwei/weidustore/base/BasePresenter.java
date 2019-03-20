package com.bwei.weidustore.base;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;

import okhttp3.RequestBody;

/**
 * @Auther: 李
 * @Date: 2019/3/19 14:11:48
 * @Description:
 */
public abstract class BasePresenter<T> {

    /**
     * 内存泄漏
     */
    private Reference reference;


    public void attch(T t){
        reference = new WeakReference(t);
    }

    public void detach(){
        if (reference.get() != null){
            reference.clear();
            reference = null;
        }
    }
    //首页
    //商品
    public abstract void getShopList();
    //轮播
    public abstract void getBannerData();
    //详情
    public abstract void getData(int commodityId);
    public abstract void getCommentData(int commodityId);
    public abstract void addShopCarData(Map<String,String> map, RequestBody body);
    //登录
    public abstract void getLogin(String phone,String pwd);
}
