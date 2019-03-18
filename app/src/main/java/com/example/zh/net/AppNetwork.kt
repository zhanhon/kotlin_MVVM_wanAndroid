package com.example.zh.net

import com.example.zh.bean.ArticleBean
import com.shehuan.wanandroid.base.net.observer.BaseObserver

/**
 * 网络请求数据
 */
class AppNetwork private constructor(){
    companion object {
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
    private val httpMethods = HttpMethods.getInstance();
    private val workService = httpMethods.getApiService()

    /**
     * 文章列表
     */
    fun articleList(pageNum: Int, baseObserver: BaseObserver<ArticleBean>)
            = workService.articleList(pageNum).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun register(userName: String,password: String,repassword: String,baseObserver: BaseObserver<String>)
            = workService.register(userName,password,repassword).compose(httpMethods.setThread()).subscribe(baseObserver)

    fun login(userName: String,password: String,baseObserver: BaseObserver<String>)
            = workService.login(userName,password).compose(httpMethods.setThread()).subscribe(baseObserver)

}