package com.example.zh.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.NavArticle
import com.example.zh.bean.NavBean
import com.example.zh.bean.NavTab
import com.example.zh.data.model.NavRepository
import com.example.zh.utils.executeRequest

class NavViewModel : BaseViewModel(){
    val navRepository by lazy { NavRepository() }
    val navFragmentList: ArrayList<NavTab> = ArrayList()
    val articleList: ArrayList<NavArticle> = ArrayList()

    fun getNavList(): LiveData<List<NavBean>>{
        val data = MutableLiveData<List<NavBean>>()
        executeRequest(
            request = {navRepository.getNavList()},
            onSuccess = {data.postValue(it.data)}
        )
        return data
    }

}