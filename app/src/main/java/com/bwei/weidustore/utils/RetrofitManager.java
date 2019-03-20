package com.bwei.weidustore.utils;


import com.bwei.weidustore.api.Api;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Auther: 李
 * @Date: 2019/2/22 09:55:04
 * @Description:
 */
public class RetrofitManager {

    private static RetrofitManager instance;
    private Retrofit retrofit;
    private static OkHttpClient httpClient;

    //静态块
    static {
        getOkhttpInstance();
    }
    private RetrofitManager() {
        initRetrofit();
    }
    private void initRetrofit(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(httpClient)
                .build();
    }

    //单例RetrofitManager
    public static RetrofitManager getRetrofitInstance(){
        if (instance==null){
            synchronized (RetrofitManager.class){
                if (instance==null){
                    instance = new RetrofitManager();
                }
            }
        }
        return instance;
    }
    //单例模式OkHttp
    private static OkHttpClient getOkhttpInstance(){
        if (httpClient==null){
            synchronized (OkHttpClient.class){
                if (httpClient==null){
                    httpClient = new OkHttpClient.Builder()
                            .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//日志拦截器
                            .readTimeout(3000, TimeUnit.SECONDS)
                            .connectTimeout(3000,TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return httpClient;
    }



    /**
     * 服务接口
     * @param reqServer
     * @param <T>
     * @return
     */
    public <T> T setCreat(Class<T> reqServer){
        return retrofit.create(reqServer);
    }

}
