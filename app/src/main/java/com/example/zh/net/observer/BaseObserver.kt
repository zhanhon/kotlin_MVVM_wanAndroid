package com.shehuan.wanandroid.base.net.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

 abstract class BaseObserver<E> : Observer<E> {
    private lateinit var disposable: Disposable

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(data: E) {
        onSuccess(data)
    }

    override fun onError(e: Throwable) {
        onFailure(e as Exception)
    }

    override fun onComplete() {

    }

    fun getDisposable() = disposable

    abstract fun onSuccess(data: E)

    abstract fun onFailure(e: Exception)
}