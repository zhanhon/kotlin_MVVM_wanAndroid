package com.example.zh.utils

import com.orhanobut.logger.Logger

object MyLog {
    private val RELEASE_VERSION_FLAG = true

    fun d(msg: Any) {
        if (RELEASE_VERSION_FLAG) {
            Logger.d(msg)
        }
    }

    fun e(msg: String) {
        if (RELEASE_VERSION_FLAG) {
            Logger.e(msg + "")
        }
    }

    fun w(msg: String) {
        if (RELEASE_VERSION_FLAG) {
            Logger.w(msg + "")
        }
    }

    fun v(msg: String) {
        if (RELEASE_VERSION_FLAG) {
            Logger.v(msg + "")
        }
    }

    fun i(msg: String) {
        if (RELEASE_VERSION_FLAG) {
            Logger.i(msg + "")
        }
    }

    fun wtf(msg: String) {
        if (RELEASE_VERSION_FLAG) {
            Logger.wtf(msg + "")
        }
    }

}
