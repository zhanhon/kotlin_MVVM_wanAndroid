package com.example.zh.net.interceptor

import com.example.zh.net.cookie.CookieStore
import com.example.zh.utils.MyLog
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl


class MyCookieJar : CookieJar {
    var cookieStore: CookieStore

    constructor(cookieStores: CookieStore){
        this.cookieStore = cookieStores
    }

    override fun saveFromResponse (url: HttpUrl, cookies: List<Cookie>) {
        cookieStore.saveCookie(url, cookies)
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore.loadCookie(url)
    }

}