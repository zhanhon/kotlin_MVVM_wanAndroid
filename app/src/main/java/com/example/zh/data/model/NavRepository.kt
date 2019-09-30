package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.BaseModel
import com.example.zh.base.Const
import com.example.zh.bean.BaseBean
import com.example.zh.bean.NavBean
import com.example.zh.net.AppNetwork
import com.example.zh.net.observer.BaseObserver

class NavRepository : BaseModel(){

    suspend fun getNavList() = workService.navList()
}