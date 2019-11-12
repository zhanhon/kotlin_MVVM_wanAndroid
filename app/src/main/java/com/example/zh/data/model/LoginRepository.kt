package com.example.zh.data.model

import com.example.zh.base.BaseModel

/**
 * 数据仓库
 */
class LoginRepository : BaseModel(){
    suspend fun login(userName: String, password: String) = workService.login(userName,password)

    suspend fun register(userName: String,password: String,repassword: String) = workService.register(userName,password,repassword)

    suspend fun logout() = workService.logout()

}