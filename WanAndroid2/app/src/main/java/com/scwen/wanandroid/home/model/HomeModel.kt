package com.scwen.wanandroid.home.model

import com.scwen.wanandroid.base.BaseModel
import com.scwen.wanandroid.net.BaseResult
import com.scwen.wanandroid.net.HttpClient
import io.reactivex.Flowable

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
class HomeModel : BaseModel() {
    fun getBanners(): Flowable<BaseResult<List<BannerBean>>> = HttpClient.api.getBanners()
}