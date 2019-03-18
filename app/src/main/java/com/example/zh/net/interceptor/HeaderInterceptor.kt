package com.example.zh.net.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .method(originalRequest.method(), originalRequest.body())
//            requestBuilder.addHeader("Authorization", "Bearer " + Const.TOKEN)//添加请求头信息，服务器进行token有效性验证
        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}