package com.example.zh.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.LoginBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

/**
 * 数据仓库
 */
class LoginRepository{
    private val appNetwork by lazy { AppNetwork() }
    fun login(userName: String,password: String) : LiveData<LoginBean>{
        val contentData = MutableLiveData<LoginBean>()
        appNetwork.login(userName,password,object : BaseObserver<BaseBean<LoginBean>>(){
            override fun onSuccess(results: BaseBean<LoginBean>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    contentData.postValue(results.data)
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
            override fun onSuccess(results: BaseBean<LoginBean>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    contentData.postValue(results.data)
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
            override fun onSuccess(results: BaseBean<String>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    contentData.postValue(results)
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

}