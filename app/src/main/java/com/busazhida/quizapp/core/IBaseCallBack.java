package com.busazhida.quizapp.core;

public interface IBaseCallBack<T> {

    void onSuccess(T result);

    void onFailure(Throwable e);
}