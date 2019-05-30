package com.scwen.wanandroid.home

import android.util.Log
import com.scwen.wanandroid.base.BasePresenter
import com.scwen.wanandroid.home.model.Article
import com.scwen.wanandroid.home.model.HomeModel
import com.scwen.wanandroid.home.model.PageBean
import com.scwen.wanandroid.net.BaseResult
import com.scwen.wanandroid.net.excute
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

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


    fun getArticles(page: Int) {
        mModel?.getArticles(page)?.excute(onSuccess = {
            mView?.showArticles(it.datas)
        }, onHandleError = { msg, needLogin ->
            mView?.loadFail(msg)
        })
    }


    fun getHomeData() {
        Flowable.zip(
            mModel?.getTop(),
            mModel?.getArticles(0),
            BiFunction<BaseResult<MutableList<Article>>, BaseResult<PageBean<Article>>, BaseResult<PageBean<Article>>> { t1, t2 ->
                t1.data.forEach {
                    it.top = 1
                }
                t2.data.datas.addAll(0, t1.data)
                return@BiFunction t2
            }).excute(onSuccess = {
            mView?.showArticles(it.datas)
        }, onHandleError = { msg, needLogin ->
            mView?.loadFail(msg)
        })
    }
}