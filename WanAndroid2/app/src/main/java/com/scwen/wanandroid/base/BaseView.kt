package com.scwen.wanandroid.base

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
interface BaseView {
    fun showLoading(str: CharSequence)

    fun hideLoading()

    fun  showCommonMag(str: CharSequence)

    fun  showSuccessMag(str: CharSequence)

    fun  showErrorMag(str: CharSequence)
}