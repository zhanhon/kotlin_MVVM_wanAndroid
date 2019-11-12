package com.example.zh.data.apis

import com.example.zh.bean.*
import retrofit2.http.*

interface WanAndroidApis {
    /**
     * 首页文章列表
     */
    @GET("article/list/{pageNum}/json")
    suspend fun articleList(@Path("pageNum") pageNum: Int): BaseBean<ArticleBean>

    /**
     * 首页banner
     */
    @GET("banner/json")
    suspend fun banner(): BaseBean<List<BannerBean>>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/register")
    suspend fun register(@Field("username")username: String,@Field("password")password: String,
                 @Field("repassword")repassword: String): BaseBean<LoginBean>

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/login")
    suspend fun login(@Field("username")username: String,@Field("password")password: String): BaseBean<LoginBean>

    /**
     * 退出
     */
    @GET("user/logout/json")
    suspend fun logout(): BaseBean<String>

    /**
     * 导航
     */
    @GET("navi/json")
    suspend fun navList(): BaseBean<List<NavBean>>

    /**
     * 体系
     */
    @GET("tree/json")
    suspend fun treeSystem(): BaseBean<List<TreeSystemBean>>

    /**
     * 知识体系下的文章
     */
    @GET("article/list/{pageNum}/json")
    suspend fun treeArticleList(@Path("pageNum") pageNum: Int,@Query("cid") cId:Int): BaseBean<TreeArticleBean>

    /**
     * 项目分类下的文章
     */
    @GET("project/list/{pageNum}/json")
    suspend fun projectArticleList(@Path("pageNum") pageNum: Int,@Query("cid") cId:Int): BaseBean<ProjectArticleBean>

    /**
     * 项目分类
     */
    @GET("project/tree/json")
    suspend fun getProject(): BaseBean<List<ProjectData>>

    /**
     * 收藏文章列表
     */
    @GET("lg/collect/list/{pageNum}/json")
    suspend fun likeArticleList(@Path("pageNum") pageNum: Int): BaseBean<ArticleBean>

    //收藏站内文章
    @POST("lg/collect/{id}/json")
    suspend fun likeArticle(@Path("id") id: Int): BaseBean<String>

    //取消收藏（文章列表）
    @POST("lg/uncollect_originId/{id}/json")
    suspend fun cancelArticle(@Path("id") id: Int): BaseBean<String>

    //取消收藏（我的页面）
    @POST("lg/uncollect/{id}/json")
    @FormUrlEncoded
    suspend fun cancelUncollect(@Path("id") id: Int,@Field("originId") originId: Int = -1): BaseBean<String>

}