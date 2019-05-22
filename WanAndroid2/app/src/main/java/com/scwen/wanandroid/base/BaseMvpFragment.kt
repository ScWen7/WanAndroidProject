package com.scwen.wanandroid.base

/**
 * Created by scwen on 2019/5/22.
 *  QQ ：811733738
 *  作用：
 */
abstract class BaseMvpFragment<V : BaseView, P : IPresenter<V>> : BaseFragment() {

    protected var mPresenter: P? = null

    override fun initView() {
        mPresenter = createPresenter()
        mPresenter?.attachView(this as V)
    }

    override fun onDestroyView() {
        mPresenter?.detachView()
        mPresenter = null
        super.onDestroyView()
    }

    abstract fun createPresenter(): P
}