package com.example.zh.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.data.model.HomeRepository
import com.example.zh.utils.ToastUtil
import com.example.zh.utils.executeRequest

class HomeViewModel : BaseViewModel(){
    val homeRepository by lazy { HomeRepository() }

    var pageNum: Int = 0
    val mList: ArrayList<ArticleBean.DatasBean> = ArrayList()
    val mBannerList: ArrayList<BannerBean> = ArrayList()
    val mTitleList: ArrayList<String> = ArrayList()

    fun getArticleList(pageNum: Int): LiveData<List<ArticleBean.DatasBean>>{
        val contentData = MutableLiveData<List<ArticleBean.DatasBean>>()
        executeRequest(
            request = { homeRepository.getArticleList(pageNum) },
            onSuccess = { contentData.postValue(it.data?.datas) }
        )
        return contentData
    }

    fun getBanner(): LiveData<List<BannerBean>>{
        val liveData = MutableLiveData<List<BannerBean>>()
        executeRequest(
            request = { homeRepository.getBanner() },
            onSuccess = { liveData.postValue(it.data) }
        )
        return liveData
    }

    fun likeArticle(id: Int): LiveData<String>{
        val contentData = MutableLiveData<String>()
        executeRequest(
            request = { homeRepository.likeArticle(id) },
            onSuccess = {
                if (it.isSuccess()){
                    contentData.postValue("收藏成功")
                }else{
                    contentData.postValue(null)
                    ToastUtil.showToast("请先登录")
                }
            },
            onFail = { contentData.postValue(null) }


        )
        return contentData
    }


    fun cancelUncollect(id: Int,originId: Int): LiveData<String>{
        val contentData = MutableLiveData<String>()
        executeRequest(
            request = { homeRepository.cancelUncollect(id, originId) },
            onSuccess = {
                if (it.isSuccess()){
                    contentData.postValue("取消成功")
                }else{
                    contentData.postValue(null)
                    ToastUtil.showToast("请先登录")
                }
            },
            onFail = {contentData.postValue(null)}
        )
        return contentData
    }


    fun cancelArticle(id: Int): LiveData<String>{
        val contentData = MutableLiveData<String>()
        executeRequest(
            request = { homeRepository.cancelArticle(id) },
            onSuccess = {
                if (it.isSuccess()){
                    contentData.postValue("取消成功")
                }else{
                    contentData.postValue(null)
                    ToastUtil.showToast("请先登录")
                }
            },
            onFail = {contentData.postValue(null)}
        )
        return contentData
    }


    fun likeArticleList(pageNum: Int): LiveData<List<ArticleBean.DatasBean>>{
        val contentData = MutableLiveData<List<ArticleBean.DatasBean>>()
        executeRequest(
            request = { homeRepository.likeArticleList(pageNum) },
            onSuccess = { contentData.postValue(it.data?.datas) }
        )
        return contentData
    }

}