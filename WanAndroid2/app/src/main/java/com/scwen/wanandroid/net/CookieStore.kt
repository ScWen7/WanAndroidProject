package com.scwen.wanandroid.net

import okhttp3.Cookie
import okhttp3.HttpUrl

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
interface CookieStore {
    fun saveCookie(url: HttpUrl, cookie: List<Cookie>)

    fun saveCookie(url: HttpUrl, cookie: Cookie)

    fun loadCookie(url: HttpUrl): MutableList<Cookie>

    fun getAllCookie(): List<Cookie>

    /** 获取当前url对应的所有的cookie  */
     fun getCookie(url: HttpUrl): List<Cookie>

    /** 根据url和cookie移除对应的cookie  */
    fun removeCookie(url: HttpUrl, cookie: Cookie): Boolean

    /** 根据url移除所有的cookie  */
    fun removeCookie(url: HttpUrl): Boolean

    /** 移除所有的cookie  */
    fun removeAllCookie(): Boolean
}