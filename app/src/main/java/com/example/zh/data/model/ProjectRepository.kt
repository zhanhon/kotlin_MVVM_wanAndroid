package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.ProjectData
import com.example.zh.net.AppNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProjectRepository{
    private val appNetwork by lazy { AppNetwork() }

    /**
     * 项目分类
     */
    fun getProjectList(): LiveData<List<ProjectData>> {
        val contentData = MutableLiveData<List<ProjectData>>()

        GlobalScope.launch(Dispatchers.Main) {
            val result = withContext(Dispatchers.IO) {  appNetwork.getProject() }
            if (result.isSuccess()){
                contentData.postValue(result.data)
            }else{
                contentData.postValue(null)
            }
        }
        return contentData
    }

    suspend fun getProject() = appNetwork.getProject()

    /**
     * 项目分类下的文章
     */
    fun projectArticleList(pageNum: Int,cId: Int): LiveData<List<ArticleBeanData>>{
        val contentData = MutableLiveData<List<ArticleBeanData>>()
        GlobalScope.launch {
            try {
                val results = withContext(Dispatchers.IO) {
                    appNetwork.projectArticleList(pageNum,cId)
                }
                contentData.postValue(results.data?.datas)
            }catch (e: Exception){
                e.printStackTrace()
                contentData.postValue(null)
            }
        }
        return contentData
    }



}