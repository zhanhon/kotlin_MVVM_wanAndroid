package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.BaseBean
import com.example.zh.bean.NavBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

class NavRepository constructor(private val appNetwork: AppNetwork){
    companion object {
        private var instance: NavRepository? = null
        fun getInstance(network: AppNetwork): NavRepository{
            if (instance == null){
                synchronized(NavRepository::class.java){
                    if (instance == null){
                        instance = NavRepository(network);
                    }
                }
            }

            return instance!!
        }
    }

    fun getNavList(): LiveData<List<NavBean>>{
        val data = MutableLiveData<List<NavBean>>()
        appNetwork.navList(object : BaseObserver<BaseBean<List<NavBean>>>(){
            override fun onSuccess(results: BaseBean<List<NavBean>>) {
                if (results.errorCode == 0){
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