package com.example.zh.net

import com.example.zh.bean.*
import com.example.zh.net.observer.BaseObserver

/**
 * 网络请求数据
 */
class AppNetwork {
    private val httpMethods = HttpMethods.getInstance()
    private val workService = httpMethods.getApiService()

    /**
     * 文章列表
     */
    fun articleList(pageNum: Int, baseObserver: BaseObserver<BaseBean<ArticleBean>>)
            = workService.articleList(pageNum).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun likeArticleList(pageNum: Int, baseObserver: BaseObserver<BaseBean<ArticleBean>>)
            = workService.likeArticleList(pageNum).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun banner(baseObserver: BaseObserver<BaseBean<List<BannerBean>>>)
            = workService.banner().compose(httpMethods.setThread()).subscribe(baseObserver)

    fun likeArticle(id: Int,baseObserver: BaseObserver<BaseBean<String>>)
            = workService.likeArticle(id).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun cancelArticle(id: Int,baseObserver: BaseObserver<BaseBean<String>>)
            = workService.cancelArticle(id).compose(httpMethods.setThread()).subscribe(baseObserver)


}