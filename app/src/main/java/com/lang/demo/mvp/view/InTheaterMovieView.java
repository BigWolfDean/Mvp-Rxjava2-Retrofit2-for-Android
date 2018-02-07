package com.lang.demo.mvp.view;

/**
 * Created by lang on 2018/2/6.
 * 继承自IBaseView
 * 声明View层方法
 */

public interface InTheaterMovieView extends IBaseView {

    //设置从网络上获取到的结果
    void setResponse(String response);

    //设置错误信息 包含系统返回的错误和开发者自定义的错误
    void setError(String error);
}
