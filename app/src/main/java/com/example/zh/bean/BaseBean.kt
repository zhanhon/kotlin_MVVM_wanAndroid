package com.example.zh.bean

data class BaseBean<T>(
    var errorCode: Int = -1,
    var errorMsg: String? = null,
    var data: T? = null
){
    fun isSuccess() = errorCode == 0
}