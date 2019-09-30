package com.example.zh.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zh.net.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 协程请求封装
 */
fun <T>ViewModel.executeRequest(
    onStart: () -> Unit = {},
    request: suspend () -> T,
    onSuccess: (T) -> Unit,
    onFail: (Exception) -> Unit = {},
    onComplete: () -> Unit = {}
){
    onStart.invoke()
    viewModelScope.launch {
        try {
            val res: T = withContext(Dispatchers.IO) { request.invoke() }
            res?.let {
                onSuccess.invoke(it)
            }
        }catch (e: Exception) {
            e.printStackTrace()
            val msg = ApiException.exceptionHandler(e)
            onFail.invoke(e)
        }finally {
            onComplete.invoke()
        }
    }
}