package com.example.zh.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ProjectArticleBean
import com.example.zh.bean.ProjectData
import com.example.zh.data.model.ProjectRepository
import java.util.ArrayList

class ProjectViewModel(private val projectRepository: ProjectRepository) : BaseViewModel(){
    val projectList : ArrayList<ProjectData> = ArrayList()
    val articleList : ArrayList<ProjectArticleBean> = ArrayList()

    fun getProjectList() = projectRepository.getProjectList()

    fun projectArticleList(pageNum: Int,cId: Int) = projectRepository.projectArticleList(pageNum,cId)



    /**
     * vm传参
     */
    @Suppress("UNCHECKED_CAST")
    class ProjectViewModelFactory(private val projectRepository: ProjectRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ProjectViewModel(projectRepository) as T
        }
    }
}