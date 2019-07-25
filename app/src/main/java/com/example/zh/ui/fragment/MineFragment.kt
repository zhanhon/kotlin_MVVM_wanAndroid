package com.example.zh.ui.fragment


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.example.zh.R
import com.example.zh.base.BaseFragment
import com.example.zh.base.BaseSharePerence
import com.example.zh.bean.LoginBean
import com.example.zh.ui.LoginActivity
import com.example.zh.ui.home.LikeActivity
import com.example.zh.ui.viewmodel.LoginViewModel
import com.example.zh.utils.InjectorUtil
import com.example.zh.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_mine.*


/**
 *个人中心
 */
class MineFragment : BaseFragment() {
    lateinit var viewModel: LoginViewModel

    override fun setContent(): Int {
        return R.layout.fragment_mine
    }

    override fun initVM() {
        viewModel = ViewModelProviders.of(this, InjectorUtil.getLoginViewModelFactory()).get(LoginViewModel::class.java)

    }

    override fun initView() {
        tv_login.setOnClickListener {
            startActivity(Intent(context,LoginActivity::class.java))
        }

        btn_logout.setOnClickListener {
            viewModel.logout().observe(this, Observer {
                if (it != null && it.errorCode == 0){
                    ToastUtil.showToast("退出登录")
                    BaseSharePerence.getInstance().setUserInfo(null)
                    setUserInfo()
                }else{
                    ToastUtil.showToast("退出登录失败")
                }
            })
        }

        tv_like.setOnClickListener {
            val loginBean: LoginBean? = viewModel.mSharePerence.getUserInfo()
            if (loginBean != null){
                startActivity(Intent(context,LikeActivity::class.java))
            }else{
                startActivity(Intent(context,LoginActivity::class.java))
            }
        }

    }

    override fun initData() {

    }

    override fun onStart() {
        super.onStart()
        setUserInfo()
    }

    override fun onVisible() {
        super.onVisible()
        setUserInfo()
    }

    fun setUserInfo(){
        val loginBean: LoginBean? = viewModel.mSharePerence.getUserInfo()
        tv_login.text = loginBean?.username ?: "登录/注册"
        if (loginBean != null){
            btn_logout.visibility = View.VISIBLE
        }else{
            btn_logout.visibility = View.GONE
        }
    }

}
