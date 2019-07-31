package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.ProjectArticleBean
import com.example.zh.bean.ProjectData
import com.example.zh.data.model.LoginRepository
import com.example.zh.data.model.ProjectRepository
import java.text.ParsePosition
import java.util.ArrayList

class ProjectViewModel : BaseViewModel(){
    private val projectRepository by lazy { ProjectRepository() }

    val projectList : ArrayList<ProjectData> = ArrayList()
    val articleList : ArrayList<ArticleBeanData> = ArrayList()
    var pageNum: Int = 1
    var cid: Int = -1
    fun getProjectList() = projectRepository.getProjectList()

    fun projectArticleList(pageNum: Int,cId: Int) = projectRepository.projectArticleList(pageNum,cId)


    fun selectionProject(position: Int): Int{
        if (projectList.size > position){
            pageNum = 1
            cid = projectList[position].id
        }
         return cid
    }

}