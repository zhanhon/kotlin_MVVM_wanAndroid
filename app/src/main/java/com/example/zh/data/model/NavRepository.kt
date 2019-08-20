package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.NavBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

class NavRepository{

    val appNetwork by lazy { AppNetwork() }

    fun getNavList(): LiveData<List<NavBean>>{
        val data = MutableLiveData<List<NavBean>>()
        appNetwork.navList(object : BaseObserver<BaseBean<List<NavBean>>>(){
            override fun onSuccess(results: BaseBean<List<NavBean>>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    data.postValue(results.data)
                }else{
                    data.postValue(null)
                }
            }
            override fun onFailure(e: Exception) {
                data.postValue(null)
            }
        })
        return data
    }
}