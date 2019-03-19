package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ArticleBean
import com.example.zh.bean.BannerBean
import com.example.zh.data.model.HomeRepository

class HomeViewModel(private val homeRepository: HomeRepository) : BaseViewModel(){
    var pageNum: Int = 0
    val mList: ArrayList<ArticleBean.DatasBean> = ArrayList()
    val mBannerList: ArrayList<BannerBean> = ArrayList()
    val mTitleList: ArrayList<String> = ArrayList()

    fun getArticleList(pageNum: Int) = homeRepository.getArticleList(pageNum)

    fun getBanner() = homeRepository.getBanner()


    /**
     * vm传参
     */
    class HomeViewModelFactory(private val repository: HomeRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return HomeViewModel(repository) as T
        }
    }
}