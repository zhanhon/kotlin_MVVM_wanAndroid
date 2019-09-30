package com.example.zh.data.model

import com.example.zh.base.BaseModel

class TreeRepository : BaseModel(){
    suspend fun treeSystem() = workService.treeSystem()


    suspend fun treeArticleList(numPage: Int,cId: Int) = workService.treeArticleList(numPage,cId)


}