package com.example.zh.ui.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseFragment
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.NavArticle
import com.example.zh.bean.NavBean
import com.example.zh.bean.NavTab
import com.example.zh.data.model.NavRepository

class NavViewModel(private val navRepository: NavRepository) : BaseViewModel(){
    /**
     * vm传参
     */
    @Suppress("UNCHECKED_CAST")
    class NavViewModelFactory(private val repository: NavRepository) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return NavViewModel(repository) as T
        }
    }

    val navFragmentList: ArrayList<NavTab> = ArrayList()
    val articleList: ArrayList<NavArticle> = ArrayList()

    fun getNavList() = navRepository.getNavList()

}