package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.bean.BaseBean
import com.example.zh.net.AppNetwork
import com.example.zh.net.observer.BaseObserver
import com.example.zh.utils.ToastUtil

/**
 * 数据仓库
 */
class HomeRepository{
    val appNetwork by lazy { AppNetwork() }

    fun getBanner(): LiveData<List<BannerBean>>{
        val liveData = MutableLiveData<List<BannerBean>>()
        appNetwork.banner(object : BaseObserver<BaseBean<List<BannerBean>>>(){
            override fun onSuccess(results: BaseBean<List<BannerBean>>) {
                if (results.isSuccess()){
                    liveData.postValue(results.data)
                }else{
                    liveData.postValue(null)
                }
            }
            override fun onFailure(e: Exception) {
                liveData.postValue(null)
            }

        })

        return liveData
    }

    fun getArticleList(pageNum: Int): LiveData<List<ArticleBean.DatasBean>>{
        val contentData = MutableLiveData<List<ArticleBean.DatasBean>>()
        appNetwork.articleList(pageNum,object: BaseObserver<BaseBean<ArticleBean>>(){
            override fun onSuccess(results: BaseBean<ArticleBean>) {
                if (results.isSuccess()){
                    contentData.postValue(results.data?.datas)
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

    fun likeArticle(id: Int): LiveData<String>{
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

    fun cancelUncollect(id: Int,originId: Int): LiveData<String>{
        val contentData = MutableLiveData<String>()
        appNetwork.cancelUncollect(id,originId,object: BaseObserver<BaseBean<String>>(){
            override fun onSuccess(results: BaseBean<String>) {
                if (results.isSuccess()){
                    contentData.postValue("取消成功")
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

    fun cancelArticle(id: Int): LiveData<String>{
        val contentData = MutableLiveData<String>()
        appNetwork.cancelArticle(id,object: BaseObserver<BaseBean<String>>(){
            override fun onSuccess(results: BaseBean<String>) {
                if (results.isSuccess()){
                    contentData.postValue("取消成功")
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

    fun likeArticleList(pageNum: Int): LiveData<List<ArticleBean.DatasBean>>{
        val contentData = MutableLiveData<List<ArticleBean.DatasBean>>()
        appNetwork.likeArticleList(pageNum,object : BaseObserver<BaseBean<ArticleBean>>() {
            override fun onSuccess(results: BaseBean<ArticleBean>) {
                if (results.isSuccess()){
                    contentData.postValue(results.data?.datas)
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

