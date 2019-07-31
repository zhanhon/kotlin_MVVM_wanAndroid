package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.ProjectData
import com.example.zh.net.AppNetwork
import kotlinx.coroutines.*

class ProjectRepository {
    private val appNetwork by lazy { AppNetwork() }

    /**
     * 协程
     * 项目分类
     */
    fun getProjectList(): LiveData<List<ProjectData>> {
        val contentData = MutableLiveData<List<ProjectData>>()
        GlobalScope.launch(Dispatchers.Main) {
            val result = GlobalScope.async(Dispatchers.IO) {
                appNetwork.getProject().body()?.data
            }
            val response = result.await()
            contentData.value = response
        }
        return contentData
    }

    /**
     * 项目分类下的文章
     */
    fun projectArticleList(pageNum: Int,cId: Int): LiveData<List<ArticleBeanData>>{
        val contentData = MutableLiveData<List<ArticleBeanData>>()
        GlobalScope.launch(Dispatchers.Main){
            val result = GlobalScope.async(Dispatchers.IO){
                appNetwork.projectArticleList(pageNum,cId).body()?.data?.datas
            }
            val response = result.await()
            contentData.value = response
        }
        return contentData
    }



}