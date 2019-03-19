package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseSharePerence
import com.example.zh.base.BaseViewModel
import com.example.zh.data.model.LoginRepository

class LoginViewModel(private val loginRepository: LoginRepository) : BaseViewModel(){
    /**
     * vm传参
     */
    class LoginViewModelFactory(private val repository: LoginRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return LoginViewModel(repository) as T
        }
    }

    val mSharePerence: BaseSharePerence = BaseSharePerence.getInstance();


    fun register(userName: String,password: String,repassword: String) = loginRepository.register(userName,password,repassword)

    fun login(userName: String,password: String) = loginRepository.login(userName,password)

    fun logout() = loginRepository.logout()

}