package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.TreeArticleBean
import com.example.zh.bean.TreeSystemBean
import com.example.zh.data.model.TreeRepository

class TreeViewModel(private val treeRepository: TreeRepository) : BaseViewModel(){
    /**
     * vm传参
     */
    @Suppress("UNCHECKED_CAST")
    class TreeViewModelFactory(private val repository: TreeRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TreeViewModel(repository) as T
        }
    }

    var treeList: ArrayList<TreeSystemBean> = ArrayList()
    var treeArticleList: ArrayList<TreeArticleBean> = ArrayList()

    fun treeSystem() = treeRepository.treeSystem()

    fun treeArticleList(numPage: Int,cId: Int) = treeRepository.treeArticleList(numPage,cId)

}