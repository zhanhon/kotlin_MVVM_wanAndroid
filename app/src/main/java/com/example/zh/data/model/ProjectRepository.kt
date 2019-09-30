package com.example.zh.data.model

import com.example.zh.base.BaseModel

class ProjectRepository : BaseModel(){
    /**
     * 项目分类
     */
    suspend fun getProject() = workService.getProject()

    /**
     * 项目分类下的文章
     */
    suspend fun projectArticleList(pageNum: Int,cId: Int) = workService.projectArticleList(pageNum, cId)



}