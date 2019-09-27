package com.example.zh.net.interceptor

import com.example.zh.net.HttpMethods
import com.example.zh.utils.NetUtil
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * 缓存拦截器
 */
class CacheInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!NetUtil.isNetworkConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build()
        }
        val response = chain.proceed(request)
        if (NetUtil.isNetworkConnected()) {
            val maxAge = 0
            // 有网络时 设置缓存超时时间0个小时
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader(HttpMethods.CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                    .build()
        } else {
            // 无网络时，设置超时为3天
            val maxStale = 60 * 60 * 24 * 3
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader(HttpMethods.CACHE_NAME)
                    .build()
        }
        return response
    }

}