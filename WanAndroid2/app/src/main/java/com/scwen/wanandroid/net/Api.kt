package com.scwen.wanandroid.net

import com.google.gson.JsonElement
import com.scwen.wanandroid.home.model.Article
import com.scwen.wanandroid.home.model.BannerBean
import com.scwen.wanandroid.home.model.PageBean
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
interface Api {
    @GET("banner/json")
    fun getBanners(): Flowable<BaseResult<List<BannerBean>>>

    /**
     * 获取首页置顶文章列表
     * http://www.wanandroid.com/article/top/json
     */
    @GET("article/top/json")
    fun getTopArticles(): Flowable<BaseResult<MutableList<Article>>>

    /**
     * 获取文章列表
     * http://www.wanandroid.com/article/list/0/json
     * @param page  页码
     */
    @GET("article/list/{page}/json")
    fun getArticles(@Path("page") page: Int): Flowable<BaseResult<PageBean<Article>>>

}