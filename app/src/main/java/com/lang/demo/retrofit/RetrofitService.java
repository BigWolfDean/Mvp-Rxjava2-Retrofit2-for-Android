package com.lang.demo.retrofit;

import com.lang.demo.bean.InTheaterBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by lang on 2018/02/06.
 * RetrofitService接口
 */

public interface RetrofitService {


    /**
     * @param token 固定值`0b2bdeda43b5688921839c8ecb20399b`
     * @param city  所在城市，例如`北京`、`上海`等
     * @param start 分页使用，表示第几页
     * @param count 分页使用，表示数量
     * @return Observable实例
     */
    @GET("movie/in_theaters")
    Observable<InTheaterBean> getMovieInTheater(@Query("apikey") String token,
                                                @Query("city") String city,
                                                @Query("start") int start,
                                                @Query("count") int count);


}
