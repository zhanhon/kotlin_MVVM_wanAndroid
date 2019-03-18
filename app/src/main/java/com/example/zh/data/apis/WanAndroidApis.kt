package com.shehuan.wanandroid.apis

import com.example.zh.bean.ArticleBean
import io.reactivex.Observable
import retrofit2.http.*

interface WanAndroidApis {
    /**
     * 首页文章列表
     */
    @GET("article/list/{pageNum}/json")
    fun articleList(@Path("pageNum") pageNum: Int): Observable<ArticleBean>

    /**
     * 注册
     */
    @POST("user/register")
    fun register(@Field("username")username: String,@Field("password")password: String,@Field("repassword")repassword: String): Observable<String>

    /**
     * 登录
     */
    @POST("user/login")
    fun login(@Field("username")username: String,@Field("password")password: String): Observable<String>
}