package com.example.zh.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.zh.ui.widget.LoadingDialog

abstract class BaseActivity: AppCompatActivity(){

    var lodingDialog : LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lodingDialog = LoadingDialog(this)
    }

    abstract fun initVM()
    abstract fun initView()

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