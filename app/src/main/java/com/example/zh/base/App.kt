package com.example.zh.base

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        BaseAppContext.init(this)
    }

    companion object {
        private lateinit var instance: App
        fun getApp(): App {
            return instance
        }
    }
}