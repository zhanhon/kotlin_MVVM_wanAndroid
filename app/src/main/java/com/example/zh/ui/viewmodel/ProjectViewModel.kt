package com.example.zh.ui.viewmodel

import androidx.lifecycle.*
import com.example.zh.base.BaseViewModel
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.ProjectData
import com.example.zh.data.model.ProjectRepository
import com.example.zh.utils.executeRequest
import java.util.ArrayList

class ProjectViewModel : BaseViewModel(){
    private val projectRepository by lazy { ProjectRepository() }

    val projectList : ArrayList<ProjectData> = ArrayList()
    val articleList : ArrayList<ArticleBeanData> = ArrayList()
    var pageNum: Int = 1
    var cid: Int = -1

    fun getProjectList(): LiveData<List<ProjectData>> {
        val contentData = MutableLiveData<List<ProjectData>>()
        executeRequest(
            request = { projectRepository.getProject() },
            onSuccess = { contentData.postValue(it.data) }
        )
        return contentData
    }

    fun projectArticleList(pageNum: Int,cId: Int): LiveData<List<ArticleBeanData>>{
        val contentData = MutableLiveData<List<ArticleBeanData>>()
        executeRequest(
            request = {projectRepository.projectArticleList(pageNum, cId)},
            onSuccess = { contentData.postValue(it.data?.datas) }
        )
        return contentData
    }



    fun selectionProject(position: Int): Int{

        if (projectList.size > position){
            pageNum = 1
            cid = projectList[position].id
        }
         return cid
    }

}