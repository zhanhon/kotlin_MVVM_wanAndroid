package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseModel
import com.example.zh.bean.BaseBean
import com.example.zh.net.AppNetwork
import com.example.zh.net.observer.BaseObserver
import com.example.zh.utils.ToastUtil

class TreeRepository : BaseModel(){
    val appNetwork by lazy { AppNetwork() }
    suspend fun treeSystem() = workService.treeSystem()

    suspend fun treeArticleList(numPage: Int,cId: Int) = workService.treeArticleList(numPage,cId)

    fun likeArticle(id: Int): LiveData<String> {
        val contentData = MutableLiveData<String>()
        appNetwork.likeArticle(id,object: BaseObserver<BaseBean<String>>(){
            override fun onSuccess(results: BaseBean<String>) {
                if (results.isSuccess()){
                    contentData.postValue("收藏成功")
                }else{
                    contentData.postValue(null)
                    ToastUtil.showToast("请先登录")
                }
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }

    fun cancelArticle(id: Int): LiveData<String> {
        val contentData = MutableLiveData<String>()
        appNetwork.cancelArticle(id,object: BaseObserver<BaseBean<String>>(){
            override fun onSuccess(results: BaseBean<String>) {
                if (results.isSuccess()){
                    contentData.postValue("取消收藏")
                }else{
                    contentData.postValue(null)
                    ToastUtil.showToast("请先登录")
                }
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }
}