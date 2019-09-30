package com.example.zh.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.*
import com.example.zh.data.model.TreeRepository
import com.example.zh.utils.executeRequest

class TreeViewModel : BaseViewModel(){
    val treeRepository by lazy { TreeRepository() }
    var numPage : Int = 0

    var treeList: ArrayList<TreeSystemBean> = ArrayList()
    var treeArticleList: ArrayList<TreeArticleList> = ArrayList()

    fun treeSystem() : LiveData<List<TreeSystemBean>>{
        val data = MutableLiveData<List<TreeSystemBean>>()
        executeRequest(
            request = { treeRepository.treeSystem() },
            onSuccess = { data.postValue(it.data) }
        )
        return data
    }

    fun treeArticleList(numPage: Int,cId: Int): LiveData<TreeArticleBean>{
        val data = MutableLiveData<TreeArticleBean>()
        executeRequest(
            request = { treeRepository.treeArticleList(numPage,cId) },
            onSuccess = { data.postValue(it.data) }
        )
        return data
    }


}