package com.shehuan.wanandroid.base.net.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

 abstract class BaseObserver<E> : Observer<E> {
    private lateinit var disposable: Disposable

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(results: E) {
        onSuccess(results)
    }

    override fun onError(e: Throwable) {
        onFailure(e as Exception)
        e.printStackTrace()
    }

    override fun onComplete() {

    }

    fun getDisposable() = disposable

    abstract fun onSuccess(results: E)

    abstract fun onFailure(e: Exception)
}