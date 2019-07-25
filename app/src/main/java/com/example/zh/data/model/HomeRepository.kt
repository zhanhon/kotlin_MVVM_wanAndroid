package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.bean.BaseBean
import com.example.zh.net.AppNetwork
import com.shehuan.wanandroid.base.net.observer.BaseObserver
import kotlinx.coroutines.*

/**
 * 数据仓库
 */
class HomeRepository private constructor(private val appNetwork: AppNetwork){

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
            override fun onSuccess(results: BaseBean<List<BannerBean>>) {
                if (results.errorCode == Const.CODE_SUCCESS){
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
                if (results.errorCode == Const.CODE_SUCCESS){
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

