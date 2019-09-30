package com.example.zh.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseSharePerence
import com.example.zh.base.BaseViewModel
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.LoginBean
import com.example.zh.data.model.LoginRepository
import com.example.zh.net.observer.BaseObserver
import com.example.zh.utils.executeRequest

class LoginViewModel : BaseViewModel(){
    private val loginRepository by lazy { LoginRepository() }
    val mSharePerence: BaseSharePerence = BaseSharePerence.getInstance();


    fun register(userName: String,password: String,repassword: String) : LiveData<LoginBean>{
        val liveData = MutableLiveData<LoginBean>()
        executeRequest(
            request = { loginRepository.register(userName,password,repassword) },
            onSuccess = { liveData.postValue(it.data) }
        )
        return liveData
    }


    fun login(userName: String,password: String) : LiveData<LoginBean> {
        val contentData = MutableLiveData<LoginBean>()
        executeRequest(
            request = { loginRepository.login(userName, password) },
            onSuccess = { contentData.postValue(it.data) }
        )
        return contentData
    }


    fun logout() : LiveData<BaseBean<String>>{
        val liveData = MutableLiveData<BaseBean<String>>()
        executeRequest(
            request = {loginRepository.logout()},
            onSuccess = {liveData.postValue(it)}
        )
        return liveData
    }

}