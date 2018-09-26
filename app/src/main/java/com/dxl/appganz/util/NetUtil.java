package com.dxl.appganz.util;

import com.dxl.appganz.data.Constants;
import com.dxl.appganz.interf.GankApi;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dxl on 2018/9/26 22:56.
 */
public class NetUtil {

    private static GankApi sGankApi;
    public static GankApi getGankApi(){
        if (sGankApi == null) {
            sGankApi = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build().create(GankApi.class);
        }
        return sGankApi;
    }

}
