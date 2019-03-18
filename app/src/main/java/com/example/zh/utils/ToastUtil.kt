package com.example.zh.utils

import android.content.Context
import android.widget.Toast
import com.example.zh.base.App

object ToastUtil {
    fun showToast(context: Context?, content: String?) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, content: Int) {
        Toast.makeText(context, context.getString(content), Toast.LENGTH_SHORT).show()
    }

    fun showToast(content: String?) {
        Toast.makeText(App.getApp(), content, Toast.LENGTH_SHORT).show()
    }

    fun showToast(content: Int) {
        Toast.makeText(App.getApp(), App.getApp().getString(content), Toast.LENGTH_SHORT).show()
    }
}