package com.example.zh.data.model

import com.example.zh.base.BaseModel

/**
 * 数据仓库
 */
class HomeRepository : BaseModel(){

    suspend fun getBanner() = workService.banner()

    suspend fun getArticleList(pageNum: Int) = workService.articleList(pageNum)

    suspend fun cancelArticle(id: Int) = workService.cancelArticle(id)

    suspend fun likeArticle(id: Int) = workService.likeArticle(id)

    suspend fun cancelUncollect(id: Int,originId: Int) = workService.cancelUncollect(id, originId)

    suspend fun likeArticleList(pageNum: Int) = workService.likeArticleList(pageNum)

}

