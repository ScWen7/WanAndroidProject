package com.scwen.wanandroid.net

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
class CookieJarImpl : CookieJar {
    private lateinit var cookieStore: CookieStore

    constructor(cookieStore: CookieStore) {
        this.cookieStore = cookieStore
    }


    override fun saveFromResponse(url: HttpUrl, cookies: MutableList<Cookie>) {
        cookieStore.saveCookie(url, cookies)
    }

    override fun loadForRequest(url: HttpUrl): MutableList<Cookie> {
        return cookieStore.loadCookie(url)
    }
}