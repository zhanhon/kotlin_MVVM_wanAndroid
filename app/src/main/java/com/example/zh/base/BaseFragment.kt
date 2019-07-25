package com.example.zh.base


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.zh.ui.widget.LoadingDialog


/**
 *导航
 */
abstract class BaseFragment: Fragment() {
    var lodingDialog : LoadingDialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(setContent(), container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lodingDialog = LoadingDialog(context!!)
        initVM()
        initView()
        initData()
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

    protected fun showLoadingDialog() {
        lodingDialog?.show()
    }

    protected fun dismissLoadingDialog() {
        if (lodingDialog?.isShowing()!!) {
            lodingDialog?.dismiss()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoadingDialog()
    }
}
