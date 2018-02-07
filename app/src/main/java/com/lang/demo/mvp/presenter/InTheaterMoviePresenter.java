package com.lang.demo.mvp.presenter;

import android.content.Intent;

import com.lang.demo.model.InTheaterModel;
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

    public void getMovieInfo(String apiKey, String city, int start, int count) {
        dataManager.getMovieInTheater(apiKey, city, start, count).compose(RxSchedulers.<InTheaterModel>compose()).subscribe(new BaseObserver<InTheaterModel>() {
            @Override
            protected void onHandleSuccess(InTheaterModel inTheaterModel) {
                movieView.setResponse(inTheaterModel.toString());
            }

            @Override
            protected void onHandleError(Throwable e) {
                movieView.setError(e.getMessage());
            }

        });
    }

    public void setErrorInfo(String errorInfo) {
        movieView.setError(errorInfo);
    }
}
