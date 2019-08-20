package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.NavTab
import com.example.zh.bean.TreeArticleBean
import com.example.zh.bean.TreeArticleList
import com.example.zh.bean.TreeSystemBean
import com.example.zh.data.model.TreeRepository

class TreeViewModel : BaseViewModel(){
    val treeRepository by lazy { TreeRepository() }
    var numPage : Int = 0

    var treeList: ArrayList<TreeSystemBean> = ArrayList()
    var treeArticleList: ArrayList<TreeArticleList> = ArrayList()

    fun treeSystem() = treeRepository.treeSystem()

    fun treeArticleList(numPage: Int,cId: Int) = treeRepository.treeArticleList(numPage,cId)


}