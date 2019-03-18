package com.example.zh.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.ArticleBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

/**
 * 数据仓库
 */
class LoginRepository private constructor(private val appNetwork: AppNetwork){

    fun login(userName: String,password: String) : LiveData<String>{
        val contentData = MutableLiveData<String>()
        appNetwork.login(userName,password,object : BaseObserver<String>(){
            override fun onSuccess(data: String) {
                Log.e("log","data="+data)
                contentData.postValue(data)
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }

    fun register(userName: String,password: String,repassword: String) : LiveData<String>{
        val contentData = MutableLiveData<String>()
        appNetwork.register(userName,password,repassword,object : BaseObserver<String>(){
            override fun onSuccess(data: String) {
                Log.e("log","data register="+data)
                contentData.postValue(data)
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }



    companion object {
        private var instance: LoginRepository? = null
        fun getInstance(network: AppNetwork): LoginRepository {
            if (instance == null) {
                synchronized(LoginRepository::class.java) {
                    if (instance == null) {
                        instance = LoginRepository(network)
                    }
                }
            }
            return instance!!
        }

    }

}