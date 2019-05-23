package com.scwen.wanandroid.net

import com.google.gson.JsonElement
import com.scwen.wanandroid.home.model.BannerBean
import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
interface Api {
    @GET("banner/json")
    fun getBanners(): Flowable<BaseResult<List<BannerBean>>>
}