package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseModel
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.LoginBean
import com.example.zh.net.AppNetwork
import com.example.zh.net.observer.BaseObserver

/**
 * 数据仓库
 */
class LoginRepository : BaseModel(){
    suspend fun login(userName: String, password: String) = workService.login(userName,password)

    suspend fun register(userName: String,password: String,repassword: String) = workService.register(userName,password,repassword)

    suspend fun logout() = workService.logout()

}