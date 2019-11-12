package com.example.zh.net

import com.example.zh.bean.*
import com.example.zh.net.observer.BaseObserver

/**
 * 网络请求数据
 */
class AppNetwork {
    private val httpMethods = HttpMethods.getInstance()
    private val workService = httpMethods.getApiService()



}