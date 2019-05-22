package com.scwen.wanandroid.base

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
interface IPresenter<in V : BaseView> {
    fun attachView(view: V)

    fun detachView()
}