package com.lang.demo.retrofit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lang on 2017/11/21.
 * Retrofit工厂类
 */

public class RetrofitFactory {

    private static final String BASE_URL = "https://api.douban.com/v2/";  // 根路由

    private static final long TIMEOUT = 30;  //超时时间，此处单位为秒

    // Retrofit是基于OkHttpClient的，可以创建一个OkHttpClient进行一些配置
    private static OkHttpClient httpClient = new OkHttpClient.Builder()
            // 添加通用的Header
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(@NonNull Chain chain) throws IOException {
                    //此处可根据项目实际需求自行设置header内容
                    Request.Builder builder = chain.request().newBuilder();
                    builder.addHeader("key", "value");
                    return chain.proceed(builder.build());
                }
            })
            /*
            这里可以添加一个HttpLoggingInterceptor，因为Retrofit封装好了从Http请求到解析，
            出了bug很难找出来问题，添加HttpLoggingInterceptor拦截器方便调试接口
             */
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NonNull String message) {
                    Log.e("HttpLoggingInterceptor", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY)) //此处级别有NONE，BASIC,HEADERS和BODY
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS) //连接超时时间，单位秒
            .readTimeout(TIMEOUT, TimeUnit.SECONDS) //连接超时时间，单位秒
            .build();

    private static RetrofitService retrofitService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            // 添加Gson转换器
            .addConverterFactory(GsonConverterFactory.create(buildGson()))
            // 添加Retrofit到RxJava的转换器
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(httpClient)
            .build()
            .create(RetrofitService.class);


    public static RetrofitService getInstance() {
        return retrofitService;
    }

    private static Gson buildGson() {
        return new GsonBuilder()
                .serializeNulls()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();
    }

}
