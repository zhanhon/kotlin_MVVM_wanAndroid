package com.example.zh.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alibaba.fastjson.JSON
import com.example.zh.R
import com.example.zh.base.BaseActivity
import com.example.zh.base.BaseSharePerence
import com.example.zh.ui.viewmodel.LoginViewModel
import com.example.zh.utils.InjectorUtil
import com.example.zh.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(){
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

        btn_login.setOnClickListener{
            val name = edit_name.text.toString().trim()
            val passworid = edit_password.text.toString().trim()
            login(name,passworid)
        }

        btn_register.setOnClickListener {
            val name = edit_name.text.toString().trim()
            val passworid = edit_password.text.toString().trim()
            register(name,passworid,passworid)
        }


    }

    fun login(name : String,password: String){
        viewModel.login(name,password).observe(this, Observer {
            if (it != null){
                viewModel.mSharePerence.setUserInfo(JSON.toJSONString(it))
                finish()
            }else{
                ToastUtil.showToast("登录失败")
            }
        })
    }

    fun register(name: String,password: String,repassword: String){
        viewModel.register(name,password,repassword).observe(this, Observer {
            if (it != null){
                viewModel.mSharePerence.setUserInfo(JSON.toJSONString(it))
                finish()
            }else{
                ToastUtil.showToast("注册失败")
            }

        })
    }



}
