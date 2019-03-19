package com.example.zh.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.example.zh.R
import com.example.zh.base.BaseFragment


/**
 *导航
 */
class NavFragment : BaseFragment() {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initVM()
        initView()
        initData()
    }

    override fun setContent(): Int {
        return R.layout.fragment_nav
    }

    override fun initView() {
    }

    override fun initVM() {
    }

    override fun initData() {

    }

}
