package com.scwen.wanandroid.home

import com.scwen.wanandroid.base.BaseView
import com.scwen.wanandroid.home.model.BannerBean

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
interface HomeView : BaseView {
    fun showBanners(banners: List<BannerBean>)
}