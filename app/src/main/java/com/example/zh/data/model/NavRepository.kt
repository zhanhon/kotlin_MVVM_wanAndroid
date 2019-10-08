package com.example.zh.data.model

import com.example.zh.base.BaseModel

class NavRepository : BaseModel(){

    suspend fun getNavList() = workService.navList()
}