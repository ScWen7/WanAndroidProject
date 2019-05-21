package com.scwen.wanandroid.base

/**
 * Created by scwen on 2019/5/21.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseMvpActivity<M : BaseModel, V : BaseView, P : BasePresenter<M, V>> : BaseActivity() {
    var mPresenter: P? = null
    override fun initActivity() {
        mPresenter = createPresenter()
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        mPresenter = null
        super.onDestroy()
    }

    abstract fun createPresenter(): P
}