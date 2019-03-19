package com.example.zh.net


import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory
import com.example.zh.base.App
import com.example.zh.base.Const
import com.example.zh.net.interceptor.CacheInterceptor
import com.example.zh.net.interceptor.HeaderInterceptor
import com.example.zh.net.interceptor.MyCookieJar
import com.shehuan.wanandroid.apis.WanAndroidApis

import java.io.File
import java.util.concurrent.TimeUnit

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

/**
 * Created by 眼神 on 2018/3/27.
 */

class HttpMethods private constructor() {

    private val BASE_URL = Const.WAN_ANDROID_UTL
    /**
     * 获取retrofit
     * @return
     */
    private var retrofit: Retrofit? = null
        private set
    /**
     * 获取httpService
     *
     * @return
     */

    /**
     * 请求失败重连次数
     */
    private val RETRY_COUNT = 0
    private val okHttpBuilder: OkHttpClient.Builder

    companion object {
        val CACHE_NAME = "cacheData"
        private var INSTANCE: HttpMethods? = null
        fun getInstance(): HttpMethods {
            if (INSTANCE == null) {
                synchronized(OkHttpClient::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = HttpMethods()
                    }
                }
            }
            return INSTANCE!!
        }
    }


    init {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = OkHttpClient.Builder()
        val cacheFile = File(App.getApp().getExternalCacheDir(), CACHE_NAME)
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
        okHttpBuilder.cache(cache).addInterceptor(CacheInterceptor())//设置缓存
        okHttpBuilder.addInterceptor(HeaderInterceptor())//设置头信息
        //配置log打印拦截器
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpBuilder.addInterceptor(loggingInterceptor)
        okHttpBuilder.cookieJar(MyCookieJar())

        /**
         * 设置超时和重新连接
         */
        okHttpBuilder.connectTimeout(10, TimeUnit.SECONDS)
        okHttpBuilder.readTimeout(10, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(10, TimeUnit.SECONDS)
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true)


        retrofit = Retrofit.Builder()
                .client(okHttpBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())//json转换成JavaBean
                .addConverterFactory(Retrofit2ConverterFactory())//json转换成JavaBean
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
    }

    private val wanAndroidApis = create(WanAndroidApis::class.java)

    fun getApiService(): WanAndroidApis{
        return wanAndroidApis
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit!!.create(serviceClass)


    /**
     * 使用其他 baseUrl
     */
    fun<T> changeBaseUrl(baseUrl: String,serviceClass: Class<T>): T{
        val retrofit: Retrofit = Retrofit.Builder()
                .client(okHttpBuilder.build())
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(Retrofit2ConverterFactory())//json转换成JavaBean
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build()
        return retrofit.create(serviceClass)
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    fun <E> toSubscribe(o: Observable<E>, s: Observer<E>) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT.toLong())//请求失败重连次数
                .subscribe(s)
    }

    fun <T> setThread(): ObservableTransformer<T, T> {
        return object: ObservableTransformer<T,T>{
            override fun apply(upstream: Observable<T>): ObservableSource<T> {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }

        }
    }


}