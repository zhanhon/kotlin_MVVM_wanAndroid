package com.example.zh.ui.viewmodel

import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.data.model.HomeRepository

class HomeViewModel : BaseViewModel(){
    val homeRepository by lazy { HomeRepository() }

    var pageNum: Int = 0
    val mList: ArrayList<ArticleBean.DatasBean> = ArrayList()
    val mBannerList: ArrayList<BannerBean> = ArrayList()
    val mTitleList: ArrayList<String> = ArrayList()

    fun getArticleList(pageNum: Int) = homeRepository.getArticleList(pageNum)

    fun getBanner() = homeRepository.getBanner()

    fun likeArticle(id: Int) = homeRepository.likeArticle(id)
    fun cancelArticle(id: Int) = homeRepository.cancelArticle(id)
    fun cancelUncollect(id: Int,originId: Int) = homeRepository.cancelUncollect(id,originId)

    fun likeArticleList(pageNum: Int) = homeRepository.likeArticleList(pageNum)

}