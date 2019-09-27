package com.example.zh.net.observer

import com.example.zh.net.ApiException
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

 abstract class BaseObserver<T> : Observer<T> {
    private lateinit var disposable: Disposable

    override fun onSubscribe(d: Disposable) {
        disposable = d
    }

    override fun onNext(results: T) {
        onSuccess(results)
    }

    override fun onError(e: Throwable) {
        val msg = ApiException.exceptionHandler(e)
        onFailure(e as Exception)
        e.printStackTrace()
    }

    override fun onComplete() {

    }

    fun getDisposable() = disposable

    abstract fun onSuccess(results: T)

    abstract fun onFailure(e: Exception)
}