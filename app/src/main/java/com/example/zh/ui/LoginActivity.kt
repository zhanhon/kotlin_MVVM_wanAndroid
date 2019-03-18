package com.example.zh.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.zh.R
import com.example.zh.base.BaseActivity
import com.example.zh.ui.viewmodel.LoginViewModel
import com.example.zh.utils.InjectorUtil

class LoginActivity : BaseActivity() {
    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initVM()
        initView()
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this,InjectorUtil.getLoginViewModelFactory()).get(LoginViewModel::class.java)
    }

    override fun initView() {

    }
}
