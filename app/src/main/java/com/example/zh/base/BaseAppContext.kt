package com.example.zh.base

import android.content.Context

/**
 * Created by Leo on 2017/12/22.
 */

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
