package com.scwen.wanandroid.base

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseMvpActivity<V : BaseView, P : IPresenter<V>> : BaseActivity() {

    protected var mPresenter: P? = null

    override fun initActivity() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        mPresenter = null
        super.onDestroy()
    }

    abstract fun createPresenter(): P
}