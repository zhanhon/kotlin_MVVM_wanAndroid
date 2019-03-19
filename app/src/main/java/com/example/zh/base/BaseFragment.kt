package com.example.zh.base


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 *导航
 */
abstract class BaseFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setContent(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    abstract fun setContent(): Int
    abstract fun initView()
    abstract fun initVM()
    abstract fun initData()

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {//隐藏
            onInvisible()
        } else {//显示
            onVisible()
        }
    }

    open fun onInvisible() {}
    open fun onVisible() {}
}
