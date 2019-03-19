package com.example.zh.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.BaseBean
import com.example.zh.bean.LoginBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

/**
 * 数据仓库
 */
class LoginRepository private constructor(private val appNetwork: AppNetwork){

    fun login(userName: String,password: String) : LiveData<LoginBean>{
        val contentData = MutableLiveData<LoginBean>()
        appNetwork.login(userName,password,object : BaseObserver<BaseBean<LoginBean>>(){
            override fun onSuccess(data: BaseBean<LoginBean>) {
                Log.e("log","data="+data)
                if (data.errorCode == 0){
                    contentData.postValue(data.data)
                }else{
                    contentData.postValue(null)
                }
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }

    fun register(userName: String,password: String,repassword: String) : LiveData<LoginBean>{
        val contentData = MutableLiveData<LoginBean>()
        appNetwork.register(userName,password,repassword,object : BaseObserver<BaseBean<LoginBean>>(){
            override fun onSuccess(data: BaseBean<LoginBean>) {
                Log.e("log","data register="+data)
                if (data.errorCode == 0){
                    contentData.postValue(data.data)
                }else{
                    contentData.postValue(null)
                }
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }

    fun logout() : LiveData<BaseBean<String>>{
        val contentData = MutableLiveData<BaseBean<String>>()
        appNetwork.logout(object : BaseObserver<BaseBean<String>>(){
            override fun onSuccess(data: BaseBean<String>) {
                Log.e("log","data logout="+data)
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