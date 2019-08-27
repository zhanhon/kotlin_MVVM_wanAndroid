package com.example.zh.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zh.base.Const
import com.example.zh.bean.ArticleBeanData
import com.example.zh.bean.BaseBean
import com.example.zh.bean.ProjectArticleBean
import com.example.zh.bean.ProjectData
import com.example.zh.net.AppNetwork
import com.example.zh.net.observer.BaseObserver

class ProjectRepository {
    private val appNetwork by lazy { AppNetwork() }

    /**
     * 项目分类
     */
    fun getProjectList(): LiveData<List<ProjectData>> {
        val contentData = MutableLiveData<List<ProjectData>>()
        appNetwork.getProject(object : BaseObserver<BaseBean<List<ProjectData>>>(){
            override fun onSuccess(results: BaseBean<List<ProjectData>>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    contentData.postValue(results.data)
                }else{
                    contentData.postValue(null)
                }
            }

            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })
        return contentData
    }

    /**
     * 项目分类下的文章
     */
    fun projectArticleList(pageNum: Int,cId: Int): LiveData<List<ArticleBeanData>>{
        val contentData = MutableLiveData<List<ArticleBeanData>>()
        appNetwork.projectArticleList(pageNum,cId,object : BaseObserver<BaseBean<ProjectArticleBean>>(){
            override fun onSuccess(results: BaseBean<ProjectArticleBean>) {
                if (results.errorCode == Const.CODE_SUCCESS){
                    contentData.postValue(results.data?.datas)
                }else{
                    contentData.postValue(null)
                }
            }
            override fun onFailure(e: Exception) {
                contentData.postValue(null)
            }
        })

        return contentData
    }



}