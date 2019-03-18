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
class HomeRepository private constructor(val appNetwork: AppNetwork){

    companion object {
        private var instance: HomeRepository? = null
        fun getInstance(network: AppNetwork): HomeRepository {
            if (instance == null) {
                synchronized(HomeRepository::class.java) {
                    if (instance == null) {
                        instance = HomeRepository(network)
                    }
                }
            }
            return instance!!
        }

    }

    fun getArticleList(pageNum: Int): LiveData<List<ArticleBean.DataBean.DatasBean>>{
        val contentData = MutableLiveData<List<ArticleBean.DataBean.DatasBean>>()
        appNetwork.articleList(pageNum,object: BaseObserver<ArticleBean>(){
            override fun onSuccess(data: ArticleBean) {
                Log.e("log onSuccess",data.data?.toString())
                contentData.postValue(data.data?.datas)
            }
            override fun onFailure(e: Exception) {
                e.printStackTrace()
                contentData.postValue(null)
            }
        })
        return contentData
    }


}