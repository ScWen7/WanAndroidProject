package com.scwen.wanandroid.home

import android.util.Log
import com.scwen.wanandroid.base.BasePresenter
import com.scwen.wanandroid.home.model.HomeModel
import com.scwen.wanandroid.net.excute

/**
 * Created by scwen on 2019/5/23.
 *  QQ ：811733738
 *  作用：
 */
class HomePresenter : BasePresenter<HomeModel, HomeView>() {

    override fun createModel(): HomeModel? = HomeModel()

    fun getBanners() {
        mModel?.getBanners()?.excute(onSuccess = {
            mView?.showBanners(it)
        })
    }
}