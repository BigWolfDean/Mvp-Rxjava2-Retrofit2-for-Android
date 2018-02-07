package com.lang.demo.mvp.presenter;

import android.content.Intent;

import com.lang.demo.mvp.view.IBaseView;

/**
 * Created by lang on 2017/12/8.
 * 利用IPresenter来管理业务流程
 * onCreate 可用来做初始化操作，如类的实例化等
 * onStart  可用来做初始化后的操作，如变量的赋值等
 * onPause  根据项目需要决定内容
 * onStop   同上
 * attachView   绑定继承自IBaseView的接口，用于view层的展示
 * attachInComingIntent 绑定从外部传进来的Intent，对Intent相关进行操作
 * 推荐使用顺序  onCreate -> onStart -> attachView -> attachingInComingIntent -> onPause -> onStop
 * 注意： 使用的过程中一定要根据自己制定的流程来进行操作，否则位置一乱就会发生各种错误
 */

public interface IPresenter {

    void onCreate();

    void onStart();

    void onPause();

    void onStop();

    // 绑定view
    void attachView(IBaseView view);

    // 绑定intent
    void attachIncomingIntent(Intent intent);
}
