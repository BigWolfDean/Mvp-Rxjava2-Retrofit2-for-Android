package com.lang.demo.mvp.manager;

import com.lang.demo.model.InTheaterModel;
import com.lang.demo.retrofit.RetrofitFactory;
import com.lang.demo.retrofit.RetrofitService;

import io.reactivex.Observable;

/**
 * Created by lang on 2017/12/8.
 * 数据管理类
 * 用于获取retrofitService的实例及生成并返回对应接口的Observable实例
 */

public class DataManager {


    private RetrofitService retrofitService;

    public DataManager() {
        this.retrofitService = RetrofitFactory.getInstance();
    }

    /**
     * 此处返回 Observable实例，由Presenter层调用
     *
     * @param apiKey
     * @param city
     * @param start
     * @param count
     * @return
     */
    public Observable<InTheaterModel> getMovieInTheater(String apiKey, String city, int start, int count) {
        return retrofitService.getMovieInTheater(apiKey, city, start, count);
    }


}
