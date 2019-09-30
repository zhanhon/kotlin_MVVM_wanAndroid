package com.example.zh.base

import com.example.zh.net.HttpMethods

open class BaseModel {
    private val httpMethods = HttpMethods.getInstance()
    val workService = httpMethods.getApiService()


}