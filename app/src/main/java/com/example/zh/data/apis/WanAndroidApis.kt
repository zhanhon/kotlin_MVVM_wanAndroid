package com.shehuan.wanandroid.apis

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


}