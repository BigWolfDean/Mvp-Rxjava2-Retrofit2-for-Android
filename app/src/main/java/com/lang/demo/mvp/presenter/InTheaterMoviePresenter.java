package com.lang.demo.mvp.presenter;

import android.content.Intent;

import com.lang.demo.bean.InTheaterBean;
import com.lang.demo.mvp.manager.DataManager;
import com.lang.demo.mvp.view.IBaseView;
import com.lang.demo.mvp.view.InTheaterMovieView;
import com.lang.demo.rx.BaseObserver;
import com.lang.demo.rx.RxSchedulers;

/**
 * Created by lang on 2018/2/6.
 * 实现IPresenter接口
 * 重写对应方法
 */

public class InTheaterMoviePresenter implements IPresenter {

    private InTheaterMovieView movieView;

    private DataManager dataManager;

    @Override
    public void onCreate() {
        dataManager = new DataManager();
    }

    @Override
    public void onStart() {

    }


    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }


    @Override
    public void attachView(IBaseView view) {
        movieView = (InTheaterMovieView) view;
    }

    @Override
    public void attachIncomingIntent(Intent intent) {

    }

    /**
     * 获取豆瓣正在上映的电影信息
     *
     * @param apiKey
     * @param city
     * @param start
     * @param count
     */
    public void getMovieInfo(String apiKey, String city, int start, int count) {
        dataManager.getMovieInTheater(apiKey, city, start, count).compose(RxSchedulers.<InTheaterBean>compose()).subscribe(new BaseObserver<InTheaterBean>() {
            @Override
            protected void onHandleSuccess(InTheaterBean inTheaterBean) {
                // 成功的操作
                movieView.setResponse(inTheaterBean.toString());
            }

            @Override
            protected void onHandleError(Throwable e) {
                // 失败的操作
                movieView.setError(e.getMessage());
            }

        });
    }

    /**
     * 设置错误信息
     *
     * @param errorInfo
     */
    public void setErrorInfo(String errorInfo) {
        movieView.setError(errorInfo);
    }
}
