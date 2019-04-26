package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.TreeArticleBean
import com.example.zh.bean.TreeSystemBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver

class TreeRepository constructor(private val appNetwork: AppNetwork){
    companion object {
        private var instance: TreeRepository? = null
        fun getInstance(network: AppNetwork): TreeRepository{
            if (instance == null){
                synchronized(TreeRepository::class.java){
                    if (instance == null){
                        instance = TreeRepository(network);
                    }
                }
            }

            return instance!!
        }
    }

    fun treeSystem(): LiveData<List<TreeSystemBean>>{
        val data = MutableLiveData<List<TreeSystemBean>>()
        appNetwork.treeSystem(object : BaseObserver<BaseBean<List<TreeSystemBean>>>(){
            override fun onSuccess(results: BaseBean<List<TreeSystemBean>>) {
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


    fun treeArticleList(numPage: Int,cId: Int): LiveData<TreeArticleBean>{
        val data = MutableLiveData<TreeArticleBean>()
        appNetwork.treeArticleList(numPage,cId,object : BaseObserver<BaseBean<TreeArticleBean>>(){
            override fun onSuccess(results: BaseBean<TreeArticleBean>) {
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