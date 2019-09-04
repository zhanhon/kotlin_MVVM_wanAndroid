package com.example.zh.data.apis

import com.example.zh.bean.*
import io.reactivex.Observable
import retrofit2.http.*

interface WanAndroidApis {
    /**
     * 首页文章列表
     */
    @GET("article/list/{pageNum}/json")
    fun articleList(@Path("pageNum") pageNum: Int): Observable<BaseBean<ArticleBean>>

    /**
     * 首页banner
     */
    @GET("banner/json")
    fun banner(): Observable<BaseBean<List<BannerBean>>>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    fun register(@Field("username")username: String,@Field("password")password: String,
                 @Field("repassword")repassword: String): Observable<BaseBean<LoginBean>>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    fun login(@Field("username")username: String,@Field("password")password: String): Observable<BaseBean<LoginBean>>

    /**
     * 退出
     */
    @GET("user/logout/json")
    fun logout(): Observable<BaseBean<String>>

    /**
     * 导航
     */
    @GET("navi/json")
    fun navList(): Observable<BaseBean<List<NavBean>>>

    /**
     * 体系
     */
    @GET("tree/json")
    fun treeSystem(): Observable<BaseBean<List<TreeSystemBean>>>

    /**
     * 知识体系下的文章
     */
    @GET("article/list/{pageNum}/json")
    fun treeArticleList(@Path("pageNum") pageNum: Int,@Query("cid") cId:Int): Observable<BaseBean<TreeArticleBean>>

    /**
     * 项目分类下的文章
     */
    @GET("project/list/{pageNum}/json")
    fun projectArticleList(@Path("pageNum") pageNum: Int,@Query("cid") cId:Int): Observable<BaseBean<ProjectArticleBean>>

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    fun getProject(): Observable<BaseBean<List<ProjectData>>>

    /**
     * 收藏文章列表
     */
    @GET("lg/collect/list/{pageNum}/json")
    fun likeArticleList(@Path("pageNum") pageNum: Int): Observable<BaseBean<ArticleBean>>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    fun likeArticle(@Path("id") id: Int): Observable<BaseBean<String>>

    //取消收藏
    @POST("lg/uncollect_originId/{id}/json")
    fun cancelArticle(@Path("id") id: Int): Observable<BaseBean<String>>
}