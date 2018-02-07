package com.lang.demo.rx;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by lang on 2017/11/21.
 * Observer基类
 */

public abstract class BaseObserver<T> implements Observer<T> {

    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T value) {
        onHandleSuccess(value);
    }

    @Override
    public void onError(Throwable e) {
        onHandleError(e);
        Log.e(TAG, "error:" + e.toString());
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }


    protected abstract void onHandleSuccess(T t);

    protected abstract void onHandleError(Throwable e);

}