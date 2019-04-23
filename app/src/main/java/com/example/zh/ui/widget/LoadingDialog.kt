package com.example.zh.ui.widget

import android.app.Dialog
import android.content.Context
import android.view.Display
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager

import com.example.zh.R


class LoadingDialog(context: Context) : Dialog(context, R.style.dialog_style) {

    init {
        init(context)
    }

    private fun init(context: Context) {
        val mInflater = LayoutInflater.from(context)
        val view = mInflater.inflate(R.layout.dialog_loading, null)
        setContentView(view)
        setCanceledOnTouchOutside(false)
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val lp = this.window!!.attributes
        lp.width = display.width - 0 //设置宽度
        this.window!!.attributes = lp
        lp.alpha = 0.8f
        window!!.attributes = lp
    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_DOWN) {
            dismiss()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }


}