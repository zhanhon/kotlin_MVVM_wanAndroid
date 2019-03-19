package com.example.zh.base

import android.content.Context
import android.content.SharedPreferences
import com.alibaba.fastjson.JSON
import com.example.zh.bean.LoginBean

/**
 * Created by admin on 2017/3/8.
 */
class BaseSharePerence {
    private var mSharedPreferences: SharedPreferences
    private constructor(context: Context){
        mSharedPreferences = context.getSharedPreferences(NAME_TUTU_SHARE, Context.MODE_PRIVATE)
    }
    companion object {
        private val NAME_TUTU_SHARE = "name_wanAndroid"   //缓存文件名
        private var mInstance: BaseSharePerence? = null
        fun getInstance(): BaseSharePerence {
            if (mInstance == null) {
                synchronized(BaseSharePerence::class.java) {
                    if (mInstance == null) {
                        mInstance = BaseSharePerence(BaseAppContext.instance())
                    }
                }
            }
            return mInstance!!
        }
    }
    fun setUserInfo(info: String?){
        mSharedPreferences.edit().putString("key_info",info).apply()
    }
    fun getUserInfo(): LoginBean?{
        val string : String? = mSharedPreferences.getString("key_info",null)
        return JSON.parseObject(string,LoginBean::class.java)
    }
}

