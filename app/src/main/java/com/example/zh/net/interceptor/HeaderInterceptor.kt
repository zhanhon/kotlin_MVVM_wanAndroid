package com.example.zh.net.interceptor

import com.example.zh.base.Const
import com.example.zh.utils.MyLog
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response = chain.proceed(originalRequest.newBuilder().build())
        MyLog.d(originalRequest.url().host())
        return response
    }

}