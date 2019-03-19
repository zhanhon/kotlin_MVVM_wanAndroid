package com.example.zh.base

import android.content.Context

class BaseAppContext {
    companion object {
        private var mContext: Context? = null
        fun instance(): Context{
            if (mContext == null) {
                throw NullPointerException("the context is null,please init AppContextUtil in Application first.")
            }else
                return mContext!!
        }
        fun init(context: Context) {
            mContext = context
        }
    }
}
