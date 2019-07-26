package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.BaseBean
import com.example.zh.bean.ProjectArticleBean
import com.example.zh.bean.ProjectData
import com.example.zh.net.AppNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProjectRepository private constructor(private val appNetwork: AppNetwork){
    companion object {
        private var instance: ProjectRepository? = null
        fun getInstance(network: AppNetwork): ProjectRepository {
            if (instance == null) {
                synchronized(ProjectRepository::class.java) {
                    if (instance == null) {
                        instance = ProjectRepository(network)
                    }
                }
            }
            return instance!!
        }
    }

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