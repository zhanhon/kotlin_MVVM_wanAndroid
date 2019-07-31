package com.example.zh.net

import com.example.zh.bean.*
import com.shehuan.wanandroid.base.net.observer.BaseObserver
import kotlinx.coroutines.Deferred
import retrofit2.Response

/**
 * 网络请求数据
 */
class AppNetwork {
    companion object {
        @Volatile
        private var network: AppNetwork? = null
        fun getInstance(): AppNetwork {
            if (network == null) {
                synchronized(AppNetwork::class.java) {
                    if (network == null) {
                        network = AppNetwork()
                    }
                }
            }
            return network!!
        }
    }
    private val httpMethods = HttpMethods.getInstance()
    private val workService = httpMethods.getApiService()

    /**
     * 文章列表
     */
    fun articleList(pageNum: Int, baseObserver: BaseObserver<BaseBean<ArticleBean>>)
            = workService.articleList(pageNum).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun banner(baseObserver: BaseObserver<BaseBean<List<BannerBean>>>)
            = workService.banner().compose(httpMethods.setThread()).subscribe(baseObserver)

    fun register(userName: String,password: String,repassword: String,baseObserver: BaseObserver<BaseBean<LoginBean>>)
            = workService.register(userName,password,repassword).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun login(userName: String,password: String,baseObserver: BaseObserver<BaseBean<LoginBean>>)
            = workService.login(userName,password).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun logout(baseObserver: BaseObserver<BaseBean<String>>)
            = workService.logout().compose(httpMethods.setThread()).subscribe(baseObserver)

    fun navList(baseObserver: BaseObserver<BaseBean<List<NavBean>>>)
            = workService.navList().compose(httpMethods.setThread()).subscribe(baseObserver)

    fun treeSystem(baseObserver: BaseObserver<BaseBean<List<TreeSystemBean>>>) =
            workService.treeSystem().compose(httpMethods.setThread()).subscribe(baseObserver)

    fun treeArticleList(pageNum: Int,cId: Int, baseObserver: BaseObserver<BaseBean<TreeArticleBean>>)
            = workService.treeArticleList(pageNum,cId).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun getProject(): Response<BaseBean<List<ProjectData>>> = workService.getProject().execute()

    fun projectArticleList(pageNum: Int,cId: Int): Response<BaseBean<ProjectArticleBean>> = workService.projectArticleList(pageNum,cId).execute()

}