package com.example.zh.utils

import com.example.zh.data.model.HomeRepository
import com.example.zh.data.model.LoginRepository
import com.example.zh.net.AppNetwork
import com.example.zh.ui.viewmodel.HomeViewModel
import com.example.zh.ui.viewmodel.LoginViewModel


object InjectorUtil {
    //创建首页仓库
    private fun getHomeViewModelRepository() = HomeRepository.getInstance(AppNetwork.getInstance())
    fun getHomeViewModelFactory() = HomeViewModel.HomeViewModelFactory(getHomeViewModelRepository())


    private fun getLoginViewmodelRepository() = LoginRepository.getInstance(AppNetwork.getInstance())
    fun getLoginViewModelFactory() = LoginViewModel.LoginViewModelFactory(getLoginViewmodelRepository())


}