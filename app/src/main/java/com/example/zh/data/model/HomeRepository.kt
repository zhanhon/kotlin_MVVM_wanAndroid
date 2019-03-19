package com.example.zh.data.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.bean.BaseBean
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

    fun getBanner(): LiveData<List<BannerBean>>{
        val liveData = MutableLiveData<List<BannerBean>>()
        appNetwork.banner(object : BaseObserver<BaseBean<List<BannerBean>>>(){
            override fun onSuccess(data: BaseBean<List<BannerBean>>) {
                Log.e("log getBanner","data="+data.toString())
                if (data.errorCode == 0){
                    liveData.postValue(data.data)
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
            override fun onSuccess(data: BaseBean<ArticleBean>) {
                Log.e("log onSuccess",data?.toString())
                contentData.postValue(data?.data?.datas ?: null)
            }
            override fun onFailure(e: Exception) {
                e.printStackTrace()
                contentData.postValue(null)
            }
        })
        return contentData
    }


}