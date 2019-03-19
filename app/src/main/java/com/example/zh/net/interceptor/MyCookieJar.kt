package com.example.zh.net.interceptor

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import android.util.Log


class MyCookieJar : CookieJar {
    private val cookieStore = HashMap<String,MutableList<Cookie>>()
    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore.put(url.host(), cookies)
        Log.e("log saveFromResponse","url="+url.toString())
        cookies.forEach {
            Log.e("log saveFromResponse","Cookie="+it.toString())
        }
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        val cookies = cookieStore[url.host()]
        return cookies ?: ArrayList()
    }
}